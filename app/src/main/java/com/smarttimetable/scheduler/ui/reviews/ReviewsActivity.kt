package com.smarttimetable.scheduler.ui.reviews

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.databinding.ActivityReviewsBinding
import com.smarttimetable.scheduler.ui.common.DashboardUiRenderer
import com.smarttimetable.scheduler.ui.contact.ContactActivity
import com.smarttimetable.scheduler.ui.details.DetailsActivity
import com.smarttimetable.scheduler.ui.main.MainActivity
import com.smarttimetable.scheduler.util.SessionManager

class ReviewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsBinding
    private val sessionManager by lazy { SessionManager(this) }
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navReviewsButton.isEnabled = false
        binding.navReviewsButton.contentDescription = getString(R.string.navigation_current_page, getString(R.string.reviews_page))

        binding.navHomeButton.setOnClickListener { openPage(MainActivity::class.java) }
        binding.navDetailsButton.setOnClickListener { openPage(DetailsActivity::class.java) }
        binding.navContactButton.setOnClickListener { openPage(ContactActivity::class.java) }
        binding.contactTeamButton.setOnClickListener { openPage(ContactActivity::class.java) }
        loadReviews()
    }

    private fun loadReviews() {
        firestore.collection("colleges").document(sessionManager.getCollegeKey())
            .collection("reviews")
            .get()
            .addOnSuccessListener { snapshot ->
                val items = snapshot.documents.sortedByDescending { it.getLong("createdAt") ?: 0L }.map {
                    Triple(
                        it.getString("author").orEmpty().ifBlank { "Faculty" },
                        it.getString("message").orEmpty(),
                        null as DashboardUiRenderer.CardActions?
                    )
                }
                DashboardUiRenderer.renderInfoCards(
                    binding.reviewsContainer,
                    items,
                    getString(R.string.reviews_page),
                    getString(R.string.reviews_empty)
                )
            }
            .addOnFailureListener {
                DashboardUiRenderer.renderInfoCards(
                    binding.reviewsContainer,
                    emptyList(),
                    getString(R.string.reviews_page),
                    getString(R.string.reviews_empty)
                )
            }
    }

    private fun openPage(target: Class<*>) {
        startActivity(Intent(this, target).putExtra("user_email", intent.getStringExtra("user_email").orEmpty()))
    }
}
