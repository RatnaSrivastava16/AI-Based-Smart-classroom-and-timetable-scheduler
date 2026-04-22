package com.smarttimetable.scheduler.ai

import com.smarttimetable.scheduler.data.Classroom
import com.smarttimetable.scheduler.data.Faculty
import com.smarttimetable.scheduler.data.Subject
import com.smarttimetable.scheduler.data.TimetableOption
import com.smarttimetable.scheduler.util.ScheduleConfig
import com.smarttimetable.scheduler.util.TimeSlot

class GreedyTimetableSolver(
    private val faculties: List<Faculty>,
    private val subjects: List<Subject>,
    private val classrooms: List<Classroom>
) : TimetableSolver {

    override val name: String = "Greedy"

    override fun generateTopTimetables(topN: Int): List<TimetableOption> {
        if (faculties.isEmpty() || subjects.isEmpty() || classrooms.isEmpty()) return emptyList()

        val sessions = SolverCore.buildRequiredSessions(subjects, faculties)
        val assignments = mutableListOf<SessionAssignment>()
        val facultyLoad = mutableMapOf<Int, Int>()

        sessions.forEachIndexed { index, pair ->
            val subject = pair.first
            val eligible = faculties.filter { it.subject.equals(subject.name, true) }
            val selectedFaculty = eligible.minByOrNull { facultyLoad[it.id] ?: 0 } ?: pair.second
            facultyLoad[selectedFaculty.id] = (facultyLoad[selectedFaculty.id] ?: 0) + 1

            val slot = TimeSlot(
                dayIndex = index % ScheduleConfig.days.size,
                periodIndex = (index / ScheduleConfig.days.size) % ScheduleConfig.periodsPerDay
            )
            val room = classrooms.maxByOrNull { it.capacity } ?: classrooms.first()

            assignments.add(
                SessionAssignment(
                    subject = subject,
                    faculty = selectedFaculty,
                    timeSlot = slot,
                    classroom = room
                )
            )
        }

        val fitness = SolverCore.fitness(assignments)
        return listOf(
            TimetableOption(
                id = 1,
                algorithmName = name,
                fitnessScore = fitness,
                slots = SolverCore.toSlots(assignments)
            )
        )
    }
}
