package com.smarttimetable.scheduler.ui.contact

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.databinding.ActivityContactBinding
import com.smarttimetable.scheduler.ui.details.DetailsActivity
import com.smarttimetable.scheduler.ui.main.MainActivity
import com.smarttimetable.scheduler.ui.reviews.ReviewsActivity

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navContactButton.isEnabled = false
        binding.navContactButton.contentDescription = getString(R.string.navigation_current_page, getString(R.string.contact_page))

        binding.navHomeButton.setOnClickListener { openPage(MainActivity::class.java) }
        binding.navDetailsButton.setOnClickListener { openPage(DetailsActivity::class.java) }
        binding.navReviewsButton.setOnClickListener { openPage(ReviewsActivity::class.java) }

        binding.emailSupportButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${getString(R.string.support_email_value)}")
                putExtra(Intent.EXTRA_SUBJECT, "Smart Scheduler Support")
            }
            startSafely(emailIntent)
        }

        binding.callSupportButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${getString(R.string.support_phone_value)}")
            }
            startSafely(callIntent)
        }
    }

    private fun startSafely(intent: Intent) {
        try {
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
        }
    }

    private fun openPage(target: Class<*>) {
        startActivity(Intent(this, target).putExtra("user_email", intent.getStringExtra("user_email").orEmpty()))
    }
}
