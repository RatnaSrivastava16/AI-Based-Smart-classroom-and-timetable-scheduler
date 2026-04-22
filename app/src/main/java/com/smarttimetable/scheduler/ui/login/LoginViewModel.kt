package com.smarttimetable.scheduler.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.smarttimetable.scheduler.data.AppDatabase
import com.smarttimetable.scheduler.util.SessionManager
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val role: String = "",
    val email: String = "",
    val collegeName: String = "",
    val facultyName: String = "",
    val message: String? = null
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val appDao = AppDatabase.getInstance(application).appDao()

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState

    fun restoreSession(role: String, identity: String, collegeName: String, facultyName: String = "") {
        if (role.isBlank() || collegeName.isBlank()) return
        _uiState.value = LoginUiState(
            isSuccess = true,
            role = role,
            email = identity,
            collegeName = collegeName,
            facultyName = facultyName
        )
    }

    fun checkFirebaseSession(collegeName: String) {
        val user = auth.currentUser ?: return
        if (user.isAnonymous) return
        _uiState.value = LoginUiState(isLoading = true)
        resolveRoleAndFinish(user.uid, user.email.orEmpty(), SessionManager.ROLE_ADMIN, collegeName.trim(), "")
    }

    fun loginWithEmail(email: String, password: String, selectedRole: String, collegeName: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState(message = "Enter valid email and password")
            return
        }
        _uiState.value = LoginUiState(isLoading = true)
        clearAnonymousSessionIfNeeded()
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener { result ->
                val user = result.user
                if (user != null) {
                    resolveRoleAndFinish(
                        uid = user.uid,
                        email = user.email.orEmpty().ifBlank { email.trim() },
                        selectedRole = if (selectedRole.isNotBlank()) selectedRole else SessionManager.ROLE_ADMIN,
                        collegeName = collegeName.trim(),
                        facultyName = ""
                    )
                } else {
                    _uiState.value = LoginUiState(message = "Login failed. Try again.")
                }
            }
            .addOnFailureListener { exception ->
                if (shouldUseOfflineAdminFallback(exception, selectedRole)) {
                    _uiState.value = LoginUiState(
                        isSuccess = true,
                        role = SessionManager.ROLE_ADMIN,
                        email = email.trim(),
                        collegeName = "",
                        message = "Firebase is unreachable right now. Continuing in offline admin mode on this device."
                    )
                } else {
                    _uiState.value = LoginUiState(message = mapAuthError(exception, isRegister = false))
                }
            }
    }

    fun registerWithEmail(email: String, password: String, selectedRole: String, collegeName: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState(message = "Enter valid email and password")
            return
        }
        _uiState.value = LoginUiState(isLoading = true)
        clearAnonymousSessionIfNeeded()
        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener { result ->
                val user = result.user
                if (user != null) {
                    persistUserRole(
                        uid = user.uid,
                        email = user.email.orEmpty().ifBlank { email.trim() },
                        role = if (selectedRole.isNotBlank()) selectedRole else SessionManager.ROLE_ADMIN,
                        collegeName = collegeName.trim()
                    )
                    _uiState.value = LoginUiState(
                        isSuccess = true,
                        role = if (selectedRole.isNotBlank()) selectedRole else SessionManager.ROLE_ADMIN,
                        email = user.email.orEmpty().ifBlank { email.trim() },
                        collegeName = collegeName.trim()
                    )
                } else {
                    _uiState.value = LoginUiState(message = "Account creation failed. Try again.")
                }
            }
            .addOnFailureListener { exception ->
                if (shouldUseOfflineAdminFallback(exception, selectedRole)) {
                    _uiState.value = LoginUiState(
                        isSuccess = true,
                        role = SessionManager.ROLE_ADMIN,
                        email = email.trim(),
                        collegeName = "",
                        message = "Firebase is unreachable right now. Admin account is opening in offline mode on this device."
                    )
                } else {
                    _uiState.value = LoginUiState(message = mapAuthError(exception, isRegister = true))
                }
            }
    }

    fun firebaseAuthWithGoogle(idToken: String, selectedRole: String, collegeName: String) {
        if (collegeName.isBlank()) {
            _uiState.value = LoginUiState(message = "Enter college name")
            return
        }
        _uiState.value = LoginUiState(isLoading = true)
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        resolveRoleAndFinish(user.uid, user.email.orEmpty(), selectedRole, collegeName.trim(), "")
                } else {
                    _uiState.value = LoginUiState(message = "Google sign-in failed")
                }
            } else {
                    _uiState.value = LoginUiState(message = mapAuthError(task.exception, isRegister = false))
                }
            }
    }

    fun loginWithGoogleEmail(email: String, selectedRole: String, collegeName: String) {
        if (email.isBlank() || collegeName.isBlank()) {
            _uiState.value = LoginUiState(message = "Unable to read Google account email")
            return
        }
        ensureCloudSession(
            onSuccess = {
                _uiState.value = LoginUiState(
                    isSuccess = true,
                    role = if (selectedRole.isNotBlank()) selectedRole else SessionManager.ROLE_ADMIN,
                    email = email.trim(),
                    collegeName = collegeName.trim()
                )
            },
            onFailure = { _uiState.value = LoginUiState(message = "Unable to start cloud session") }
        )
    }

    fun loginFaculty(facultyName: String, collegeName: String) {
        if (facultyName.isBlank() || collegeName.isBlank()) {
            _uiState.value = LoginUiState(message = "Enter faculty name and college")
            return
        }

        _uiState.value = LoginUiState(isLoading = true)
        ensureCloudSession(
            onSuccess = {
                val collegeKey = normalizeCollegeKey(collegeName)
                db.collection("colleges").document(collegeKey)
                    .collection("scheduler").document("data")
                    .get()
                    .addOnSuccessListener { snapshot ->
                        val faculties = snapshot.get("faculties") as? List<Map<String, Any?>>
                        val matchedFaculty = faculties.orEmpty().firstOrNull {
                            (it["name"] as? String).orEmpty().trim().equals(facultyName.trim(), ignoreCase = true)
                        }

                        if (matchedFaculty != null) {
                            val resolvedName = (matchedFaculty["name"] as? String).orEmpty().trim()
                            publishFacultySuccess(resolvedName, collegeName)
                        } else {
                            loginFacultyFromLocalData(facultyName, collegeName, "Faculty not found in cloud. Checking saved local data.")
                        }
                    }
                    .addOnFailureListener {
                        loginFacultyFromLocalData(facultyName, collegeName, mapFacultyLookupError(it))
                    }
            },
            onFailure = {
                loginFacultyFromLocalData(
                    facultyName,
                    collegeName,
                    "Unable to reach Firebase. Checking saved local faculty data on this device."
                )
            }
        )
    }

    fun logoutFirebaseSession() {
        auth.signOut()
    }
    
    fun currentFirebaseUserEmail(): String {
        return auth.currentUser?.email.orEmpty()
    }

    fun hasFirebaseSession(): Boolean {
        return auth.currentUser?.isAnonymous == false
    }

    fun inferStoredCollege(uid: String, onResolved: (String) -> Unit) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { onResolved(it.getString("collegeName").orEmpty()) }
            .addOnFailureListener { onResolved("") }
    }

    fun currentFirebaseUid(): String {
        return auth.currentUser?.uid.orEmpty()
    }
    
    fun inferRoleForFirebaseUser(uid: String, onResolved: (String, String) -> Unit) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener {
                onResolved(
                    it.getString("role")?.lowercase().orEmpty(),
                    it.getString("collegeName").orEmpty()
                )
            }
            .addOnFailureListener { onResolved("", "") }
    }

    fun sendPasswordReset(email: String) {
        if (email.isBlank()) {
            _uiState.value = LoginUiState(message = "Enter email to reset password")
            return
        }
        _uiState.value = LoginUiState(message = "Reset email disabled in simplified mode. Use Login directly.")
    }

    fun clearMessage() {
        val state = _uiState.value ?: LoginUiState()
        if (state.message != null) {
            _uiState.value = state.copy(message = null)
        }
    }

    private fun resolveRoleAndFinish(uid: String, email: String, selectedRole: String, collegeName: String, facultyName: String) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { snapshot ->
                val storedRole = snapshot.getString("role")?.lowercase().orEmpty()
                val storedCollege = snapshot.getString("collegeName").orEmpty()
                val role = when {
                    storedRole.isNotBlank() -> storedRole
                    selectedRole.isNotBlank() -> selectedRole
                    else -> SessionManager.ROLE_ADMIN
                }
                val resolvedCollege = when {
                    collegeName.isNotBlank() -> collegeName
                    storedCollege.isNotBlank() -> storedCollege
                    else -> ""
                }

                persistUserRole(uid, email, role, resolvedCollege)
                _uiState.value = LoginUiState(
                    isSuccess = true,
                    role = role,
                    email = email,
                    collegeName = resolvedCollege,
                    facultyName = facultyName
                )
            }
            .addOnFailureListener {
                val fallbackRole = if (selectedRole.isNotBlank()) selectedRole else SessionManager.ROLE_ADMIN
                persistUserRole(uid, email, fallbackRole, collegeName)
                _uiState.value = LoginUiState(
                    isSuccess = true,
                    role = fallbackRole,
                    email = email,
                    collegeName = collegeName,
                    facultyName = facultyName
                )
            }
    }

    private fun persistUserRole(uid: String, email: String, role: String, collegeName: String) {
        if (uid.isBlank() || role.isBlank()) return
        val payload = hashMapOf(
            "role" to role,
            "email" to email,
            "collegeName" to collegeName,
            "updatedAt" to System.currentTimeMillis()
        )
        db.collection("users").document(uid).set(payload, SetOptions.merge())
    }

    private fun normalizeCollegeKey(collegeName: String): String {
        return collegeName.trim().lowercase().replace(Regex("[^a-z0-9]+"), "-").trim('-')
    }

    private fun ensureCloudSession(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (auth.currentUser != null) {
            onSuccess()
            return
        }
        auth.signInAnonymously()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    private fun clearAnonymousSessionIfNeeded() {
        if (auth.currentUser?.isAnonymous == true) {
            auth.signOut()
        }
    }

    private fun loginFacultyFromLocalData(facultyName: String, collegeName: String, cloudMessage: String) {
        viewModelScope.launch {
            val matchedFaculty = appDao.getAllFaculty().firstOrNull {
                it.name.trim().equals(facultyName.trim(), ignoreCase = true)
            }
            _uiState.postValue(
                if (matchedFaculty != null) {
                    LoginUiState(
                        isSuccess = true,
                        role = SessionManager.ROLE_FACULTY,
                        email = matchedFaculty.name,
                        collegeName = collegeName.trim(),
                        facultyName = matchedFaculty.name
                    )
                } else {
                    LoginUiState(message = cloudMessage)
                }
            )
        }
    }

    private fun publishFacultySuccess(facultyName: String, collegeName: String) {
        _uiState.value = LoginUiState(
            isSuccess = true,
            role = SessionManager.ROLE_FACULTY,
            email = facultyName,
            collegeName = collegeName.trim(),
            facultyName = facultyName
        )
    }

    private fun shouldUseOfflineAdminFallback(exception: Exception?, selectedRole: String): Boolean {
        return selectedRole == SessionManager.ROLE_ADMIN && exception is FirebaseNetworkException
    }

    private fun mapAuthError(exception: Exception?, isRegister: Boolean): String {
        return when (exception) {
            is FirebaseNetworkException -> "Cannot reach Firebase. Check internet on the device and try again."
            is FirebaseAuthException -> when (exception.errorCode) {
                "ERROR_INVALID_EMAIL" -> "Enter a valid email address."
                "ERROR_WRONG_PASSWORD" -> "Incorrect password."
                "ERROR_USER_NOT_FOUND" -> "No account found with this email."
                "ERROR_EMAIL_ALREADY_IN_USE" -> "This email is already registered. Use Login instead."
                "ERROR_WEAK_PASSWORD" -> "Password is too weak. Use at least 6 characters."
                "ERROR_OPERATION_NOT_ALLOWED" -> "Enable Email/Password sign-in in Firebase Authentication."
                "ERROR_TOO_MANY_REQUESTS" -> "Too many attempts. Please wait and try again."
                else -> exception.localizedMessage ?: if (isRegister) "Account creation failed. Try again." else "Login failed. Try again."
            }
            else -> exception?.localizedMessage ?: if (isRegister) "Account creation failed. Try again." else "Login failed. Try again."
        }
    }

    private fun mapFacultyLookupError(exception: Exception): String {
        return when (exception) {
            is FirebaseFirestoreException -> {
                if (exception.code == FirebaseFirestoreException.Code.UNAVAILABLE) {
                    "Cannot reach college records right now. Check internet and try again."
                } else {
                    "Faculty not found for this college."
                }
            }
            is FirebaseNetworkException -> "Cannot reach Firebase. Check internet on the device and try again."
            else -> "Faculty not found for this college."
        }
    }
}
