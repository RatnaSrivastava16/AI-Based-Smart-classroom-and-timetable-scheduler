package com.smarttimetable.scheduler.ui.details

import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.data.AppDatabase
import com.smarttimetable.scheduler.data.AppRepository
import com.smarttimetable.scheduler.data.ClassGroup
import com.smarttimetable.scheduler.data.Classroom
import com.smarttimetable.scheduler.data.DashboardSnapshot
import com.smarttimetable.scheduler.data.DisplayRow
import com.smarttimetable.scheduler.data.Faculty
import com.smarttimetable.scheduler.data.Subject
import com.smarttimetable.scheduler.databinding.ActivityDetailsBinding
import com.smarttimetable.scheduler.ui.common.DashboardUiRenderer
import com.smarttimetable.scheduler.ui.contact.ContactActivity
import com.smarttimetable.scheduler.ui.main.MainActivity
import com.smarttimetable.scheduler.ui.main.MainViewModel
import com.smarttimetable.scheduler.ui.main.MainViewModelFactory
import com.smarttimetable.scheduler.ui.main.TimetableAdapter
import com.smarttimetable.scheduler.ui.reviews.ReviewsActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val adapter = TimetableAdapter()
    private val sessionManager by lazy { com.smarttimetable.scheduler.util.SessionManager(this) }
    private val weekdayOptions = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    private val selectedAvailabilityDays = linkedSetOf<String>()
    private var editingFacultyId: Int? = null
    private var editingSubjectId: Int? = null
    private var editingClassroomId: Int? = null
    private var editingClassGroupId: Int? = null
    private var latestSnapshot: DashboardSnapshot? = null
    private var latestRows: List<DisplayRow> = emptyList()
    private var showAllFaculty = false
    private var showAllSubjects = false
    private var showAllClassrooms = false
    private var showAllClassGroups = false
    private var showAllTimetable = false

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(AppRepository(AppDatabase.getInstance(this).appDao(), sessionManager.getCollegeKey()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHeader()
        setupNavigation()
        setupRecycler()
        setupAvailabilityPicker()
        setupSectionToggles()
        setupActions()
        observeViewModel()
        viewModel.loadSavedTimetable()
        viewModel.refreshDashboard()
    }

    private fun setupHeader() {
        val email = intent.getStringExtra("user_email").orEmpty()
        binding.sessionEmailText.text = getString(R.string.session_email, email.ifBlank { "Unknown" })
        binding.sessionRoleText.text = "${getString(R.string.session_role, getString(R.string.role_admin))}\n${getString(R.string.college_session, sessionManager.getCollegeName().ifBlank { "Not set" })}"
        binding.headerCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom))
        binding.navDetailsButton.isEnabled = false
        binding.navDetailsButton.contentDescription = getString(R.string.navigation_current_page, getString(R.string.details_page))
    }

    private fun setupNavigation() {
        binding.navHomeButton.setOnClickListener { openPage(MainActivity::class.java) }
        binding.navReviewsButton.setOnClickListener { openPage(ReviewsActivity::class.java) }
        binding.navContactButton.setOnClickListener { openPage(ContactActivity::class.java) }
    }

    private fun setupRecycler() {
        binding.timetableRecycler.layoutManager = LinearLayoutManager(this)
        binding.timetableRecycler.adapter = adapter
    }

    private fun setupAvailabilityPicker() {
        val openPicker = {
            val checkedItems = weekdayOptions.map { selectedAvailabilityDays.contains(it) }.toBooleanArray()
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.select_days))
                .setMultiChoiceItems(weekdayOptions.toTypedArray(), checkedItems) { _, which, isChecked ->
                    val day = weekdayOptions[which]
                    if (isChecked) {
                        selectedAvailabilityDays.add(day)
                    } else {
                        selectedAvailabilityDays.remove(day)
                    }
                }
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    binding.facultyAvailabilityInput.setText(selectedAvailabilityDays.joinToString(", "))
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }

        binding.facultyAvailabilityInput.setOnClickListener { openPicker() }
    }

    private fun setupSectionToggles() {
        binding.facultyToggleButton.setOnClickListener {
            showAllFaculty = !showAllFaculty
            renderSnapshot(latestSnapshot)
        }
        binding.subjectToggleButton.setOnClickListener {
            showAllSubjects = !showAllSubjects
            renderSnapshot(latestSnapshot)
        }
        binding.classroomToggleButton.setOnClickListener {
            showAllClassrooms = !showAllClassrooms
            renderSnapshot(latestSnapshot)
        }
        binding.classGroupToggleButton.setOnClickListener {
            showAllClassGroups = !showAllClassGroups
            renderSnapshot(latestSnapshot)
        }
        binding.timetableToggleButton.setOnClickListener {
            showAllTimetable = !showAllTimetable
            renderRows(latestRows)
        }
    }

    private fun setupActions() {
        binding.addFacultyButton.setOnClickListener {
            val name = binding.facultyNameInput.text?.toString().orEmpty()
            val subject = binding.facultySubjectInput.text?.toString().orEmpty()
            val availability = binding.facultyAvailabilityInput.text?.toString().orEmpty()
            val leaves = binding.facultyLeavesInput.text?.toString()?.toIntOrNull() ?: 0

            if (name.isBlank() || subject.isBlank()) {
                Toast.makeText(this, "Faculty name and subject required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addFaculty(name, subject, availability, leaves, editingFacultyId ?: 0)
                clearFacultyForm()
                Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
            }
        }

        binding.addSubjectButton.setOnClickListener {
            val name = binding.subjectNameInput.text?.toString().orEmpty()
            val targetClassName = binding.subjectTargetClassInput.text?.toString().orEmpty()
            val weekly = binding.subjectWeeklyInput.text?.toString()?.toIntOrNull() ?: 0

            if (name.isBlank() || targetClassName.isBlank() || weekly <= 0) {
                Toast.makeText(this, "Subject, teaching class, and weekly count are required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addSubject(name, targetClassName, weekly, editingSubjectId ?: 0)
                clearSubjectForm()
                Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
            }
        }

        binding.addRoomButton.setOnClickListener {
            val name = binding.roomNameInput.text?.toString().orEmpty()
            val capacity = binding.roomCapacityInput.text?.toString()?.toIntOrNull() ?: 0

            if (name.isBlank() || capacity <= 0) {
                Toast.makeText(this, "Valid room and capacity required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addClassroom(name, capacity, editingClassroomId ?: 0)
                clearClassroomForm()
                Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
            }
        }

        binding.addClassButton.setOnClickListener {
            val className = binding.classNameInput.text?.toString().orEmpty()
            val strength = binding.classStrengthInput.text?.toString()?.toIntOrNull() ?: 0

            if (className.isBlank() || strength <= 0) {
                Toast.makeText(this, "Class name and strength are required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addClassGroup(className, strength, editingClassGroupId ?: 0)
                clearClassGroupForm()
                Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
            }
        }

        binding.generateButton.setOnClickListener { viewModel.generateTimetable() }
    }

    private fun observeViewModel() {
        viewModel.dashboard.observe(this) { snapshot ->
            latestSnapshot = snapshot
            renderSnapshot(snapshot)
        }

        viewModel.rows.observe(this) { rows ->
            latestRows = rows
            renderRows(rows)
        }
    }

    private fun renderSnapshot(snapshot: DashboardSnapshot?) {
        snapshot ?: return
        binding.timetableCanvasView.setTimetableSlots(snapshot.timetableSlots)
        binding.facultyToggleButton.text = getString(if (showAllFaculty) R.string.show_less else R.string.show_all)
        binding.subjectToggleButton.text = getString(if (showAllSubjects) R.string.show_less else R.string.show_all)
        binding.classroomToggleButton.text = getString(if (showAllClassrooms) R.string.show_less else R.string.show_all)
        binding.classGroupToggleButton.text = getString(if (showAllClassGroups) R.string.show_less else R.string.show_all)

        DashboardUiRenderer.renderInfoCards(
                binding.facultyListContainer,
                visibleItems(snapshot.faculties, showAllFaculty).map {
                    Triple(
                        it.name,
                        getString(R.string.faculty_item_detail, it.subject, it.avgLeavesPerMonth),
                        DashboardUiRenderer.CardActions(
                            editAction = DashboardUiRenderer.CardAction(getString(R.string.edit)) { startFacultyEdit(it) },
                            deleteAction = DashboardUiRenderer.CardAction(getString(R.string.delete)) {
                                if (editingFacultyId == it.id) clearFacultyForm()
                                viewModel.deleteFaculty(it.id)
                            }
                        )
                    )
                },
                getString(R.string.full_faculty_list),
                getString(R.string.empty_faculty)
        )

        DashboardUiRenderer.renderInfoCards(
                binding.subjectListContainer,
                visibleItems(snapshot.subjects, showAllSubjects).map {
                    Triple(
                        it.name,
                        getString(R.string.subject_item_detail, it.targetClassName, it.weeklyClassesRequired),
                        DashboardUiRenderer.CardActions(
                            editAction = DashboardUiRenderer.CardAction(getString(R.string.edit)) { startSubjectEdit(it) },
                            deleteAction = DashboardUiRenderer.CardAction(getString(R.string.delete)) {
                                if (editingSubjectId == it.id) clearSubjectForm()
                                viewModel.deleteSubject(it.id)
                            }
                        )
                    )
                },
                getString(R.string.full_subject_list),
                getString(R.string.empty_subjects)
        )

        DashboardUiRenderer.renderInfoCards(
                binding.classroomListContainer,
                visibleItems(snapshot.classrooms, showAllClassrooms).map {
                    Triple(
                        it.roomName,
                        getString(R.string.room_item_detail, it.capacity),
                        DashboardUiRenderer.CardActions(
                            editAction = DashboardUiRenderer.CardAction(getString(R.string.edit)) { startClassroomEdit(it) },
                            deleteAction = DashboardUiRenderer.CardAction(getString(R.string.delete)) {
                                if (editingClassroomId == it.id) clearClassroomForm()
                                viewModel.deleteClassroom(it.id)
                            }
                        )
                    )
                },
                getString(R.string.full_classroom_list),
                getString(R.string.empty_classrooms)
        )

        DashboardUiRenderer.renderInfoCards(
                binding.classGroupListContainer,
                visibleItems(snapshot.classGroups, showAllClassGroups).map {
                    Triple(
                        it.className,
                        getString(R.string.class_item_detail, it.strength),
                        DashboardUiRenderer.CardActions(
                            editAction = DashboardUiRenderer.CardAction(getString(R.string.edit)) { startClassGroupEdit(it) },
                            deleteAction = DashboardUiRenderer.CardAction(getString(R.string.delete)) {
                                if (editingClassGroupId == it.id) clearClassGroupForm()
                                viewModel.deleteClassGroup(it.id)
                            }
                        )
                    )
                },
            getString(R.string.full_classgroup_list),
            getString(R.string.empty_classes)
        )
    }

    private fun renderRows(rows: List<DisplayRow>) {
        binding.timetableToggleButton.text = getString(if (showAllTimetable) R.string.show_less else R.string.show_all)
        adapter.submit(visibleItems(rows, showAllTimetable, defaultLimit = 10))
    }

    private fun <T> visibleItems(items: List<T>, expanded: Boolean, defaultLimit: Int = 4): List<T> {
        return if (expanded || items.size <= defaultLimit) items else items.take(defaultLimit)
    }

    private fun clearFacultyForm() {
        editingFacultyId = null
        selectedAvailabilityDays.clear()
        binding.facultyNameInput.text?.clear()
        binding.facultySubjectInput.text?.clear()
        binding.facultyAvailabilityInput.text?.clear()
        binding.facultyLeavesInput.text?.clear()
        binding.addFacultyButton.text = getString(R.string.add_faculty)
    }

    private fun clearSubjectForm() {
        editingSubjectId = null
        binding.subjectNameInput.text?.clear()
        binding.subjectTargetClassInput.text?.clear()
        binding.subjectWeeklyInput.text?.clear()
        binding.addSubjectButton.text = getString(R.string.add_subject)
    }

    private fun clearClassroomForm() {
        editingClassroomId = null
        binding.roomNameInput.text?.clear()
        binding.roomCapacityInput.text?.clear()
        binding.addRoomButton.text = getString(R.string.add_classroom)
    }

    private fun clearClassGroupForm() {
        editingClassGroupId = null
        binding.classNameInput.text?.clear()
        binding.classStrengthInput.text?.clear()
        binding.addClassButton.text = getString(R.string.add_class_group)
    }

    private fun startFacultyEdit(faculty: Faculty) {
        editingFacultyId = faculty.id
        selectedAvailabilityDays.clear()
        selectedAvailabilityDays.addAll(faculty.availability.sortedBy { weekdayOptions.indexOf(it) })
        binding.facultyNameInput.setText(faculty.name)
        binding.facultySubjectInput.setText(faculty.subject)
        binding.facultyAvailabilityInput.setText(selectedAvailabilityDays.joinToString(", "))
        binding.facultyLeavesInput.setText(faculty.avgLeavesPerMonth.toString())
        binding.addFacultyButton.text = getString(R.string.update_faculty)
    }

    private fun startSubjectEdit(subject: Subject) {
        editingSubjectId = subject.id
        binding.subjectNameInput.setText(subject.name)
        binding.subjectTargetClassInput.setText(subject.targetClassName)
        binding.subjectWeeklyInput.setText(subject.weeklyClassesRequired.toString())
        binding.addSubjectButton.text = getString(R.string.update_subject)
    }

    private fun startClassroomEdit(classroom: Classroom) {
        editingClassroomId = classroom.id
        binding.roomNameInput.setText(classroom.roomName)
        binding.roomCapacityInput.setText(classroom.capacity.toString())
        binding.addRoomButton.text = getString(R.string.update_classroom)
    }

    private fun startClassGroupEdit(classGroup: ClassGroup) {
        editingClassGroupId = classGroup.id
        binding.classNameInput.setText(classGroup.className)
        binding.classStrengthInput.setText(classGroup.strength.toString())
        binding.addClassButton.text = getString(R.string.update_class_group)
    }

    private fun openPage(target: Class<*>) {
        startActivity(Intent(this, target).putExtra("user_email", intent.getStringExtra("user_email").orEmpty()))
    }
}
