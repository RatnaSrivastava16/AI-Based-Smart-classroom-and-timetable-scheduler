package com.smarttimetable.scheduler.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.databinding.ActivityOnboardingBinding
import com.smarttimetable.scheduler.ui.login.LoginActivity
import com.smarttimetable.scheduler.util.SessionManager

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var sessionManager: SessionManager

    private val slides = listOf(
        Slide(R.string.onboard_title_1, R.string.onboard_subtitle_1),
        Slide(R.string.onboard_title_2, R.string.onboard_subtitle_2),
        Slide(R.string.onboard_title_3, R.string.onboard_subtitle_3)
    )

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        renderSlide()

        binding.nextButton.setOnClickListener {
            if (index < slides.lastIndex) {
                index++
                renderSlide()
            } else {
                completeOnboarding()
            }
        }

        binding.skipButton.setOnClickListener {
            completeOnboarding()
        }
    }

    private fun renderSlide() {
        val slide = slides[index]
        binding.onboardTitle.setText(slide.titleRes)
        binding.onboardSubtitle.setText(slide.subtitleRes)
        binding.progressDots.text = getString(R.string.onboard_dots, index + 1, slides.size)
        binding.nextButton.text = if (index == slides.lastIndex) getString(R.string.get_started) else getString(R.string.next)

        binding.onboardingCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_in))
    }

    private fun completeOnboarding() {
        sessionManager.setOnboardingSeen(true)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private data class Slide(val titleRes: Int, val subtitleRes: Int)
}
