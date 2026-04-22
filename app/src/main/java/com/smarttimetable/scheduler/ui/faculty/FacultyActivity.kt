package com.smarttimetable.scheduler.ui.faculty

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.data.AppDatabase
import com.smarttimetable.scheduler.data.AppRepository
import com.smarttimetable.scheduler.databinding.ActivityFacultyBinding
import com.smarttimetable.scheduler.ui.login.LoginActivity
import com.smarttimetable.scheduler.ui.main.MainViewModel
import com.smarttimetable.scheduler.ui.main.MainViewModelFactory
import com.smarttimetable.scheduler.ui.main.TimetableAdapter
import com.smarttimetable.scheduler.util.SessionManager

class FacultyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacultyBinding
    private val adapter = TimetableAdapter()
    private val sessionManager by lazy { SessionManager(this) }
    private val firestore = FirebaseFirestore.getInstance()

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(AppRepository(AppDatabase.getInstance(this).appDao(), sessionManager.getCollegeKey()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSessionCard()
        setupList()
        setupActions()
        viewModel.loadFacultyTimetable(sessionManager.getFacultyName())
    }

    private fun setupSessionCard() {
        val name = sessionManager.getFacultyName().ifBlank { intent.getStringExtra("user_email").orEmpty() }
        binding.sessionEmailText.text = getString(R.string.session_email, name.ifBlank { "Unknown" })
        binding.sessionRoleText.text = "${getString(R.string.session_role, getString(R.string.role_faculty))}\n${getString(R.string.college_session, sessionManager.getCollegeName().ifBlank { "Not set" })}"
        binding.headerCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom))
    }

    private fun setupList() {
        binding.facultyTimetableRecycler.layoutManager = LinearLayoutManager(this)
        binding.facultyTimetableRecycler.adapter = adapter

        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.bindingAdapterPosition
                val toPos = target.bindingAdapterPosition
                if (adapter.getItemViewType(fromPos) != 1 || adapter.getItemViewType(toPos) != 1) {
                    return false
                }
                viewModel.swapSlots(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.facultyTimetableRecycler)
    }

    private fun setupActions() {
        binding.generateButton.visibility = android.view.View.GONE
        binding.absentButton.visibility = android.view.View.GONE
        binding.absentFacultyInput.visibility = android.view.View.GONE

        binding.submitReviewButton.setOnClickListener {
            val reviewText = binding.reviewInput.text?.toString().orEmpty().trim()
            if (reviewText.isBlank()) {
                Toast.makeText(this, getString(R.string.review_hint), Toast.LENGTH_SHORT).show()
            } else {
                firestore.collection("colleges").document(sessionManager.getCollegeKey())
                    .collection("reviews")
                    .add(
                        mapOf(
                            "author" to sessionManager.getFacultyName().ifBlank { "Faculty" },
                            "collegeName" to sessionManager.getCollegeName(),
                            "message" to reviewText,
                            "createdAt" to System.currentTimeMillis()
                        )
                    )
                    .addOnSuccessListener {
                        binding.reviewInput.text?.clear()
                        Toast.makeText(this, getString(R.string.review_saved), Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to submit review", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            sessionManager.clearSession()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        viewModel.rows.observe(this) { rows ->
            adapter.submit(rows)
            binding.facultyTimetableRecycler.scheduleLayoutAnimation()
        }

        viewModel.suggestion.observe(this) { suggestion ->
            binding.suggestionText.text = suggestion
        }

        viewModel.comparisonTable.observe(this) { table ->
            binding.comparisonTableText.text = table
        }
    }
}
