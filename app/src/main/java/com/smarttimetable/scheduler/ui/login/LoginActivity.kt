package com.smarttimetable.scheduler.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.databinding.ActivityLoginBinding
import com.smarttimetable.scheduler.ui.faculty.FacultyActivity
import com.smarttimetable.scheduler.ui.main.MainActivity
import com.smarttimetable.scheduler.util.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    private val viewModel: LoginViewModel by viewModels()

    private val googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val college = binding.collegeInput.text?.toString().orEmpty()
            val idToken = account.idToken
            if (!idToken.isNullOrBlank()) {
                viewModel.firebaseAuthWithGoogle(idToken, selectedRole(), college)
            } else if (!account.email.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.google_demo_mode), Toast.LENGTH_SHORT).show()
                viewModel.loginWithGoogleEmail(account.email.orEmpty(), selectedRole(), college)
            } else {
                Toast.makeText(this, getString(R.string.google_sign_in_failed), Toast.LENGTH_SHORT).show()
            }
        } catch (_: Exception) {
            Toast.makeText(this, getString(R.string.google_sign_in_failed), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        applyEntryAnimations()
        setupRoleUi()
        bindClickListeners()
        observeState()
    }

    private fun bindClickListeners() {
        binding.roleGroup.setOnCheckedStateChangeListener { _, _ -> setupRoleUi() }

        binding.loginButton.setOnClickListener {
            val primaryInput = binding.usernameInput.text?.toString().orEmpty()
            val password = binding.passwordInput.text?.toString().orEmpty()
            val college = binding.collegeInput.text?.toString().orEmpty()

            if (selectedRole() == SessionManager.ROLE_FACULTY) {
                viewModel.loginFaculty(primaryInput, college)
            } else {
                viewModel.loginWithEmail(primaryInput, password, selectedRole(), "")
            }
        }

        binding.registerButton.setOnClickListener {
            val email = binding.usernameInput.text?.toString().orEmpty()
            val password = binding.passwordInput.text?.toString().orEmpty()
            viewModel.registerWithEmail(email, password, selectedRole(), "")
        }

        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.usernameInput.text?.toString().orEmpty()
            viewModel.sendPasswordReset(email)
        }

        binding.googleLoginButton.setOnClickListener {
            val webClientId = getGoogleWebClientId()
            val optionsBuilder = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()

            if (webClientId.isNotBlank()) {
                optionsBuilder.requestIdToken(webClientId)
            } else {
                Toast.makeText(this, getString(R.string.missing_google_client_id), Toast.LENGTH_LONG).show()
            }

            val options = optionsBuilder.build()
            val googleClient = GoogleSignIn.getClient(this, options)
            googleLauncher.launch(googleClient.signInIntent)
        }
    }

    private fun observeState() {
        viewModel.uiState.observe(this) { state ->
            binding.loginProgress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            setButtonsEnabled(!state.isLoading)

            if (state.isSuccess) {
                sessionManager.setUserRole(state.role)
                sessionManager.setCollegeName(state.collegeName)
                sessionManager.setUserIdentity(state.email)
                sessionManager.setFacultyName(state.facultyName)
                navigateByRole(state.role, state.email, state.collegeName, state.facultyName)
                return@observe
            }

            if (!state.message.isNullOrBlank()) {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                viewModel.clearMessage()
            }
        }
    }

    private fun navigateByRole(role: String, email: String, collegeName: String, facultyName: String) {
        val intent = if (role == SessionManager.ROLE_ADMIN) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, FacultyActivity::class.java)
        }
        intent.putExtra("user_role", role)
        intent.putExtra("user_email", email)
        intent.putExtra("college_name", collegeName)
        intent.putExtra("faculty_name", facultyName)
        startActivity(intent)
        finish()
    }

    private fun selectedRole(): String {
        return if (binding.roleGroup.checkedChipId == binding.roleAdmin.id) {
            SessionManager.ROLE_ADMIN
        } else {
            SessionManager.ROLE_FACULTY
        }
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.loginButton.isEnabled = enabled
        binding.googleLoginButton.isEnabled = enabled
        binding.registerButton.isEnabled = enabled
        binding.forgotPasswordButton.isEnabled = enabled
    }

    private fun setupRoleUi() {
        val isFaculty = selectedRole() == SessionManager.ROLE_FACULTY
        binding.titleLogin.text = getString(if (isFaculty) R.string.faculty_login_title else R.string.admin_login)
        binding.usernameLayout.hint = getString(if (isFaculty) R.string.faculty_full_name else R.string.username)
        binding.collegeLayout.visibility = if (isFaculty) View.VISIBLE else View.GONE
        binding.passwordLayout.visibility = if (isFaculty) View.GONE else View.VISIBLE
        binding.googleLoginButton.visibility = if (isFaculty) View.GONE else View.VISIBLE
        binding.registerButton.visibility = if (isFaculty) View.GONE else View.VISIBLE
        binding.forgotPasswordButton.visibility = if (isFaculty) View.GONE else View.VISIBLE
    }

    private fun applyEntryAnimations() {
        val cardAnim = AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom)
        val popAnim = AnimationUtils.loadAnimation(this, R.anim.pop_in)
        binding.loginCard.startAnimation(cardAnim)
        binding.loginButton.startAnimation(popAnim)
        binding.googleLoginButton.startAnimation(popAnim)
    }

    private fun getGoogleWebClientId(): String {
        val id = resources.getIdentifier("default_web_client_id", "string", packageName)
        return if (id == 0) "" else getString(id)
    }
}
