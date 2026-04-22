package com.smarttimetable.scheduler.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.databinding.ActivitySplashBinding
import com.smarttimetable.scheduler.ui.faculty.FacultyActivity
import com.smarttimetable.scheduler.ui.login.LoginActivity
import com.smarttimetable.scheduler.ui.main.MainActivity
import com.smarttimetable.scheduler.ui.onboarding.OnboardingActivity
import com.smarttimetable.scheduler.util.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sessionManager: SessionManager
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        startAnimations()

        lifecycleScope.launch {
            delay(1300)
            routeApp()
        }
    }

    private fun startAnimations() {
        binding.logoCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_in))
        binding.splashTitle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom))
    }

    private fun routeApp() {
        if (!sessionManager.isOnboardingSeen()) {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            return
        }

        val cachedRole = sessionManager.getUserRole()
        val cachedCollege = sessionManager.getCollegeName()
        if (cachedRole == SessionManager.ROLE_ADMIN) {
            navigateByRole(cachedRole)
            return
        }
        if (cachedRole == SessionManager.ROLE_FACULTY && cachedCollege.isNotBlank()) {
            navigateByRole(cachedRole)
            return
        }

        val user = auth.currentUser
        if (user == null || user.isAnonymous) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { snapshot ->
                val role = snapshot.getString("role")?.lowercase().orEmpty()
                val collegeName = snapshot.getString("collegeName").orEmpty()
                if (role == SessionManager.ROLE_ADMIN || (role == SessionManager.ROLE_FACULTY && collegeName.isNotBlank())) {
                    sessionManager.setUserRole(role)
                    sessionManager.setCollegeName(collegeName)
                    sessionManager.setUserIdentity(user.email.orEmpty())
                    navigateByRole(role)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
            .addOnFailureListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
    }

    private fun navigateByRole(role: String) {
        val email = auth.currentUser?.email.orEmpty()
        val intent = if (role == SessionManager.ROLE_ADMIN) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, FacultyActivity::class.java)
        }
        intent.putExtra("user_role", role)
        intent.putExtra("user_email", email)
        startActivity(intent)
        finish()
    }
}
