package com.smarttimetable.scheduler.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smarttimetable.scheduler.ai.GeneticTimetableSolver
import com.smarttimetable.scheduler.ai.GreedyTimetableSolver
import com.smarttimetable.scheduler.ai.SimulatedAnnealingTimetableSolver
import com.smarttimetable.scheduler.ai.SolverCore
import com.smarttimetable.scheduler.ai.TimetableSolver
import com.smarttimetable.scheduler.data.AppRepository
import com.smarttimetable.scheduler.data.ClassGroupEntity
import com.smarttimetable.scheduler.data.ClassroomEntity
import com.smarttimetable.scheduler.data.DashboardSnapshot
import com.smarttimetable.scheduler.data.DashboardStats
import com.smarttimetable.scheduler.data.DisplayRow
import com.smarttimetable.scheduler.data.FacultyEntity
import com.smarttimetable.scheduler.data.RowType
import com.smarttimetable.scheduler.data.SolverBenchmark
import com.smarttimetable.scheduler.data.SubjectEntity
import com.smarttimetable.scheduler.data.TimetableOption
import com.smarttimetable.scheduler.data.TimetableSlot
import com.smarttimetable.scheduler.data.toEntity
import com.smarttimetable.scheduler.data.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AppRepository) : ViewModel() {

    private val _rows = MutableLiveData<List<DisplayRow>>()
    val rows: LiveData<List<DisplayRow>> = _rows

    private val _suggestion = MutableLiveData<String>()
    val suggestion: LiveData<String> = _suggestion

    private val _comparisonTable = MutableLiveData<String>()
    val comparisonTable: LiveData<String> = _comparisonTable

    private val _dashboard = MutableLiveData<DashboardSnapshot>()
    val dashboard: LiveData<DashboardSnapshot> = _dashboard

    private var currentSlots: MutableList<TimetableSlot> = mutableListOf()
    private var lastOptions: List<TimetableOption> = emptyList()
    private var cachedFaculties = emptyList<com.smarttimetable.scheduler.data.Faculty>()

    fun addFaculty(name: String, subject: String, availability: String, avgLeaves: Int, id: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFaculty(
                FacultyEntity(
                    id = id,
                    name = name,
                    subject = subject,
                    availabilityCsv = availability,
                    avgLeavesPerMonth = avgLeaves
                )
            )
            refreshDashboardData()
        }
    }

    fun addSubject(name: String, targetClassName: String, weekly: Int, id: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(
                SubjectEntity(
                    id = id,
                    name = name,
                    targetClassName = targetClassName,
                    weeklyClassesRequired = weekly
                )
            )
            refreshDashboardData()
        }
    }

    fun addClassroom(name: String, capacity: Int, id: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClassroom(
                ClassroomEntity(
                    id = id,
                    roomName = name,
                    capacity = capacity
                )
            )
            refreshDashboardData()
        }
    }

    fun addClassGroup(className: String, strength: Int, id: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClassGroup(
                ClassGroupEntity(
                    id = id,
                    className = className,
                    sectionName = "",
                    strength = strength
                )
            )
            refreshDashboardData()
        }
    }

    fun deleteFaculty(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFaculty(id)
            refreshDashboardData()
        }
    }

    fun deleteSubject(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubject(id)
            refreshDashboardData()
        }
    }

    fun deleteClassroom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClassroom(id)
            refreshDashboardData()
        }
    }

    fun deleteClassGroup(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClassGroup(id)
            refreshDashboardData()
        }
    }

    fun loadSavedTimetable() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.syncFromCloud()
            val entries = repository.getTimetableEntries()
            val subjectsById = repository.getSubjects().associateBy { it.id }
            if (entries.isNotEmpty()) {
                currentSlots = entries.map { it.toModel(subjectsById) }.toMutableList()
                _rows.postValue(buildRows(currentSlots, "Manual Timetable", 0.0))
            } else {
                currentSlots = mutableListOf()
                _rows.postValue(buildRows(emptyList(), "Manual Timetable", 0.0))
            }
            refreshDashboardData()
        }
    }

    fun refreshDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshDashboardData()
        }
    }

    fun loadFacultyTimetable(facultyName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.syncFromCloud()
            val entries = repository.getTimetableEntries()
            val subjectsById = repository.getSubjects().associateBy { it.id }
            val facultySlots = entries.map { it.toModel(subjectsById) }
                .filter { it.faculty.name.equals(facultyName, ignoreCase = true) }
                .toMutableList()
            currentSlots = facultySlots
            _rows.postValue(buildRows(facultySlots, "Your Timetable", 0.0))
            refreshDashboardData()
        }
    }

    fun generateTimetable() {
        viewModelScope.launch(Dispatchers.IO) {
            val faculties = repository.getFaculty().map { it.toModel() }
            val subjects = repository.getSubjects().map { it.toModel() }
            val classrooms = repository.getClassrooms().map { it.toModel() }
            cachedFaculties = faculties

            val solvers: List<TimetableSolver> = listOf(
                GeneticTimetableSolver(faculties, subjects, classrooms),
                GreedyTimetableSolver(faculties, subjects, classrooms),
                SimulatedAnnealingTimetableSolver(faculties, subjects, classrooms)
            )

            val runResults = mutableListOf<Pair<TimetableOption, SolverBenchmark>>()
            lastOptions = emptyList()

            solvers.forEach { solver ->
                val start = System.currentTimeMillis()
                val options = solver.generateTopTimetables(3)
                val duration = System.currentTimeMillis() - start
                val best = options.firstOrNull()
                if (best != null) {
                    lastOptions = lastOptions + best
                    val benchmark = SolverBenchmark(
                        algorithm = solver.name,
                        timeTakenMs = duration,
                        conflicts = best.slots.count { it.conflict },
                        fitnessScore = best.fitnessScore
                    )
                    runResults.add(best to benchmark)
                }
            }

            val winner = runResults.maxByOrNull { it.first.fitnessScore }?.first
            if (winner != null) {
                currentSlots = winner.slots.toMutableList()
                _rows.postValue(buildRows(currentSlots, winner.algorithmName, winner.fitnessScore))
                persistCurrentSlots()
            }

            _comparisonTable.postValue(buildComparisonTable(runResults.map { it.second }))
            refreshDashboardData()
        }
    }

    fun swapSlots(fromAdapterPosition: Int, toAdapterPosition: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val rows = _rows.value.orEmpty()
            val from = rows.getOrNull(fromAdapterPosition)?.slotIndex ?: -1
            val to = rows.getOrNull(toAdapterPosition)?.slotIndex ?: -1
            if (from !in currentSlots.indices || to !in currentSlots.indices || from == to) return@launch

            val first = currentSlots[from]
            val second = currentSlots[to]

            currentSlots[from] = first.copy(
                subject = second.subject,
                faculty = second.faculty,
                classroom = second.classroom
            )
            currentSlots[to] = second.copy(
                subject = first.subject,
                faculty = first.faculty,
                classroom = first.classroom
            )

            currentSlots = recomputeConflicts(currentSlots).toMutableList()
            _rows.postValue(buildRows(currentSlots, "Manual Edit", 0.0))
            persistCurrentSlots()
        }
    }

    fun markAbsentAndSuggest(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = SolverCore.suggestAlternative(name, currentSlots, cachedFaculties)
            _suggestion.postValue(message)
        }
    }

    private fun buildRows(slots: List<TimetableSlot>, algorithmName: String, fitness: Double): List<DisplayRow> {
        val rows = mutableListOf<DisplayRow>()
        rows.add(
            DisplayRow(
                type = RowType.HEADER,
                header = "Best Solver: $algorithmName",
                fitness = if (fitness > 0) "Fitness: ${String.format("%.2f", fitness)}" else "Manual mode"
            )
        )

        val dayOrder = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
        dayOrder.forEach { day ->
            val daySlots = slots.withIndex()
                .filter { it.value.day == day }
                .sortedBy { it.value.period }

            if (daySlots.isNotEmpty()) {
                rows.add(
                    DisplayRow(
                        type = RowType.HEADER,
                        header = day,
                        fitness = "${daySlots.size} sessions"
                    )
                )
                daySlots.forEach { indexedSlot ->
                    rows.add(
                        DisplayRow(
                            type = RowType.SLOT,
                            slot = indexedSlot.value,
                            slotIndex = indexedSlot.index
                        )
                    )
                }
            }
        }
        return rows
    }

    private fun buildComparisonTable(metrics: List<SolverBenchmark>): String {
        if (metrics.isEmpty()) return "No benchmark available"
        val header = "Algorithm | Time(ms) | Conflicts | Fitness"
        val line = "------------------------------------------"
        val body = metrics.joinToString("\n") {
            "${it.algorithm} | ${it.timeTakenMs} | ${it.conflicts} | ${String.format("%.2f", it.fitnessScore)}"
        }
        return "$header\n$line\n$body"
    }

    private fun recomputeConflicts(slots: List<TimetableSlot>): List<TimetableSlot> {
        val conflictIndices = mutableSetOf<Int>()
        for (i in slots.indices) {
            for (j in i + 1 until slots.size) {
                val slotA = slots[i]
                val slotB = slots[j]
                if (slotA.day == slotB.day && slotA.period == slotB.period) {
                    if (slotA.faculty.id == slotB.faculty.id || slotA.classroom.id == slotB.classroom.id) {
                        conflictIndices.add(i)
                        conflictIndices.add(j)
                    }
                }
            }
        }
        return slots.mapIndexed { index, slot -> slot.copy(conflict = conflictIndices.contains(index)) }
    }

    private suspend fun persistCurrentSlots() {
        val entities = currentSlots.mapIndexed { index, slot -> slot.toEntity(index) }
        repository.saveTimetableEntries(entities)
    }

    private suspend fun refreshDashboardData() {
        val faculties = repository.getFaculty().map { it.toModel() }
        val subjects = repository.getSubjects().map { it.toModel() }
        val classGroups = repository.getClassGroups().map { it.toModel() }
        val classrooms = repository.getClassrooms().map { it.toModel() }
        val timetable = if (currentSlots.isNotEmpty()) {
            currentSlots.toList()
        } else {
            val subjectsById = repository.getSubjects().associateBy { it.id }
            repository.getTimetableEntries().map { it.toModel(subjectsById) }
        }

        cachedFaculties = faculties
        _dashboard.postValue(
            DashboardSnapshot(
                stats = DashboardStats(
                    facultyCount = faculties.size,
                    subjectCount = subjects.size,
                    classCount = classGroups.size,
                    classroomCount = classrooms.size,
                    timetableCount = timetable.size,
                    conflictCount = timetable.count { it.conflict },
                    totalCapacity = classrooms.sumOf { it.capacity }
                ),
                faculties = faculties,
                subjects = subjects,
                classGroups = classGroups,
                classrooms = classrooms,
                timetableSlots = timetable
            )
        )
    }
}

class MainViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
