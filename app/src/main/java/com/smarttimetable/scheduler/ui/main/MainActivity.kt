package com.smarttimetable.scheduler.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.data.AppDatabase
import com.smarttimetable.scheduler.data.AppRepository
import com.smarttimetable.scheduler.databinding.ActivityMainBinding
import com.smarttimetable.scheduler.ui.common.DashboardUiRenderer
import com.smarttimetable.scheduler.ui.contact.ContactActivity
import com.smarttimetable.scheduler.ui.details.DetailsActivity
import com.smarttimetable.scheduler.ui.login.LoginActivity
import com.smarttimetable.scheduler.ui.reviews.ReviewsActivity
import com.smarttimetable.scheduler.util.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = TimetableAdapter()
    private val sessionManager by lazy { SessionManager(this) }
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(AppRepository(AppDatabase.getInstance(this).appDao(), sessionManager.getCollegeKey()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ensureCollegeConfigured()
    }

    private fun setupSessionCard() {
        val email = intent.getStringExtra("user_email").orEmpty()
        binding.sessionEmailText.text = getString(R.string.session_email, email.ifBlank { "Unknown" })
        binding.sessionRoleText.text = "${getString(R.string.session_role, getString(R.string.role_admin))}\n${getString(R.string.college_session, sessionManager.getCollegeName().ifBlank { "Not set" })}"
        binding.headerCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom))
        binding.navHomeButton.isEnabled = false
        binding.navHomeButton.contentDescription = getString(R.string.navigation_current_page, getString(R.string.home_page))
        binding.sessionRoleText.setOnClickListener { promptCollegeName() }

        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            SessionManager(this).clearSession()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupRecycler() {
        binding.timetableRecycler.layoutManager = LinearLayoutManager(this)
        binding.timetableRecycler.adapter = adapter
        binding.generateButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_in))

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
        ItemTouchHelper(callback).attachToRecyclerView(binding.timetableRecycler)
    }

    private fun setupActions() {
        binding.generateButton.setOnClickListener {
            viewModel.generateTimetable()
        }

        binding.quickManageDataButton.setOnClickListener {
            openPage(DetailsActivity::class.java)
        }

        binding.reviewsShortcutButton.setOnClickListener {
            openPage(ReviewsActivity::class.java)
        }

        binding.contactShortcutButton.setOnClickListener {
            openPage(ContactActivity::class.java)
        }

        binding.absentButton.setOnClickListener {
            val name = binding.absentFacultyInput.text?.toString().orEmpty()
            if (name.isBlank()) {
                Toast.makeText(this, getString(R.string.enter_faculty_name), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.markAbsentAndSuggest(name)
            }
        }
    }

    private fun setupNavigation() {
        binding.navDetailsButton.setOnClickListener { openPage(DetailsActivity::class.java) }
        binding.navReviewsButton.setOnClickListener { openPage(ReviewsActivity::class.java) }
        binding.navContactButton.setOnClickListener { openPage(ContactActivity::class.java) }
    }

    private fun ensureCollegeConfigured() {
        if (sessionManager.getCollegeName().isBlank()) {
            promptCollegeName()
            return
        }
        initializeDashboard()
    }

    private fun initializeDashboard() {
        setupSessionCard()
        setupRecycler()
        setupActions()
        setupNavigation()
        observeViewModel()
        viewModel.loadSavedTimetable()
        viewModel.refreshDashboard()
    }

    private fun promptCollegeName() {
        val input = EditText(this).apply {
            hint = getString(R.string.college_name)
            setText(sessionManager.getCollegeName())
            setSelection(text?.length ?: 0)
        }

        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.set_college_title))
            .setMessage(getString(R.string.set_college_message))
            .setView(input)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.save_college)) { _, _ ->
                val collegeName = input.text?.toString().orEmpty().trim()
                if (collegeName.isBlank()) {
                    Toast.makeText(this, getString(R.string.enter_college_name), Toast.LENGTH_SHORT).show()
                    promptCollegeName()
                    return@setPositiveButton
                }
                sessionManager.setCollegeName(collegeName)
                FirebaseAuth.getInstance().currentUser?.uid?.takeIf { it.isNotBlank() }?.let { uid ->
                    firestore.collection("users").document(uid).set(
                        mapOf(
                            "collegeName" to collegeName,
                            "updatedAt" to System.currentTimeMillis()
                        ),
                        SetOptions.merge()
                    )
                }
                recreate()
            }
            .show()
    }

    private fun observeViewModel() {
        viewModel.rows.observe(this) { rows ->
            adapter.submit(rows)
            binding.timetableRecycler.scheduleLayoutAnimation()
        }

        viewModel.suggestion.observe(this) { suggestion ->
            binding.suggestionText.text = suggestion
        }

        viewModel.comparisonTable.observe(this) { table ->
            binding.comparisonTableText.text = table
        }

        viewModel.dashboard.observe(this) { snapshot ->
            binding.timetableCanvasView.setTimetableSlots(snapshot.timetableSlots)
            binding.facultyCountValue.text = snapshot.stats.facultyCount.toString()
            binding.subjectCountValue.text = snapshot.stats.subjectCount.toString()
            binding.classCountValue.text = snapshot.stats.classCount.toString()
            binding.roomCountValue.text = snapshot.stats.classroomCount.toString()
            binding.sessionCountValue.text = snapshot.stats.timetableCount.toString()
            binding.conflictCountValue.text = snapshot.stats.conflictCount.toString()
            binding.capacityValue.text = snapshot.stats.totalCapacity.toString()

            renderFacultyPreview(snapshot.faculties.take(4).map {
                Triple(
                    it.name,
                    getString(R.string.faculty_item_detail, it.subject, it.avgLeavesPerMonth),
                    null as DashboardUiRenderer.CardActions?
                )
            })

            DashboardUiRenderer.renderInfoCards(
                binding.subjectPreviewContainer,
                snapshot.subjects.take(4).map {
                    Triple(
                        it.name,
                        getString(R.string.subject_item_detail, it.targetClassName, it.weeklyClassesRequired),
                        null as DashboardUiRenderer.CardActions?
                    )
                },
                getString(R.string.subject_preview),
                getString(R.string.empty_subjects)
            )

            DashboardUiRenderer.renderInfoCards(
                binding.classPreviewContainer,
                snapshot.classGroups.take(4).map {
                    Triple(
                        it.className,
                        getString(R.string.class_item_detail, it.strength),
                        null as DashboardUiRenderer.CardActions?
                    )
                },
                getString(R.string.class_preview),
                getString(R.string.empty_classes)
            )

            DashboardUiRenderer.renderInfoCards(
                binding.classroomPreviewContainer,
                snapshot.classrooms.take(4).map {
                    Triple(
                        it.roomName,
                        getString(R.string.room_item_detail, it.capacity),
                        null as DashboardUiRenderer.CardActions?
                    )
                },
                getString(R.string.classroom_preview),
                getString(R.string.empty_classrooms)
            )
        }
    }

    private fun renderFacultyPreview(items: List<Triple<String, String, DashboardUiRenderer.CardActions?>>) {
        DashboardUiRenderer.renderInfoCards(
            binding.facultyPreviewContainer,
            items,
            getString(R.string.faculty_preview),
            getString(R.string.empty_faculty)
        )
    }

    private fun openPage(target: Class<*>) {
        startActivity(Intent(this, target).putExtra("user_email", intent.getStringExtra("user_email").orEmpty()))
    }
}
