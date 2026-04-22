package com.smarttimetable.scheduler.ai

import com.smarttimetable.scheduler.data.Classroom
import com.smarttimetable.scheduler.data.Faculty
import com.smarttimetable.scheduler.data.Subject
import com.smarttimetable.scheduler.data.TimetableSlot
import com.smarttimetable.scheduler.data.toDayName
import com.smarttimetable.scheduler.util.ScheduleConfig
import com.smarttimetable.scheduler.util.TimeSlot
import kotlin.math.max
import kotlin.math.sqrt

internal data class SessionAssignment(
    val subject: Subject,
    val faculty: Faculty,
    val timeSlot: TimeSlot,
    val classroom: Classroom
)

internal object SolverCore {
    fun buildRequiredSessions(subjects: List<Subject>, faculties: List<Faculty>): List<Pair<Subject, Faculty>> {
        val sessions = mutableListOf<Pair<Subject, Faculty>>()
        subjects.forEach { subject ->
            val eligible = faculties.filter { it.subject.equals(subject.name, ignoreCase = true) }
            if (eligible.isNotEmpty()) {
                repeat(subject.weeklyClassesRequired) {
                    sessions.add(subject to eligible.random())
                }
            }
        }
        return sessions
    }

    fun fitness(assignments: List<SessionAssignment>): Double {
        var score = 1000.0
        val bySlot = assignments.groupBy { it.timeSlot }

        bySlot.values.forEach { sessions ->
            val facultyCounts = sessions.groupingBy { it.faculty.id }.eachCount()
            val roomCounts = sessions.groupingBy { it.classroom.id }.eachCount()

            facultyCounts.values.filter { it > 1 }.forEach { conflicts ->
                score -= (conflicts - 1) * 20
            }
            roomCounts.values.filter { it > 1 }.forEach { conflicts ->
                score -= (conflicts - 1) * 15
            }
        }

        assignments.forEach { assignment ->
            val day = assignment.timeSlot.toDayName()
            if (!assignment.faculty.availability.contains(day)) {
                score -= 10
            }
            score -= assignment.faculty.avgLeavesPerMonth * 0.5
        }

        val byFacultyDay = assignments.groupBy { it.faculty.id to it.timeSlot.dayIndex }
        byFacultyDay.values.forEach { sessions ->
            if (sessions.size > 3) {
                score -= (sessions.size - 3) * 10
            }
        }

        val byFaculty = assignments.groupBy { it.faculty.id }.mapValues { it.value.size }
        if (byFaculty.isNotEmpty()) {
            val mean = byFaculty.values.average()
            val variance = byFaculty.values.map { (it - mean) * (it - mean) }.average()
            val stdDev = sqrt(variance)
            score += max(0.0, 100 - stdDev * 10)
        }

        val avgCapacity = assignments.map { it.classroom.capacity }.average()
        score += avgCapacity / 10

        return max(0.0, score)
    }

    fun toSlots(assignments: List<SessionAssignment>): List<TimetableSlot> {
        val conflicts = detectConflictKeys(assignments)
        return assignments.map { assignment ->
            val key = conflictKey(assignment)
            TimetableSlot(
                day = assignment.timeSlot.toDayName(),
                period = assignment.timeSlot.periodIndex + 1,
                subject = assignment.subject,
                faculty = assignment.faculty,
                classroom = assignment.classroom,
                conflict = conflicts.contains(key)
            )
        }.sortedWith(compareBy({ ScheduleConfig.days.indexOf(it.day) }, { it.period }))
    }

    private fun detectConflictKeys(assignments: List<SessionAssignment>): Set<String> {
        val conflictKeys = mutableSetOf<String>()
        val bySlot = assignments.groupBy { it.timeSlot }

        bySlot.values.forEach { sessions ->
            val facultyCounts = sessions.groupingBy { it.faculty.id }.eachCount()
            val roomCounts = sessions.groupingBy { it.classroom.id }.eachCount()

            sessions.forEach { assignment ->
                val day = assignment.timeSlot.toDayName()
                val facultyClash = (facultyCounts[assignment.faculty.id] ?: 0) > 1
                val roomClash = (roomCounts[assignment.classroom.id] ?: 0) > 1
                val notAvailable = !assignment.faculty.availability.contains(day)
                if (facultyClash || roomClash || notAvailable) {
                    conflictKeys.add(conflictKey(assignment))
                }
            }
        }
        return conflictKeys
    }

    private fun conflictKey(assignment: SessionAssignment): String {
        return "${assignment.timeSlot.dayIndex}-${assignment.timeSlot.periodIndex}-${assignment.faculty.id}-${assignment.classroom.id}-${assignment.subject.id}"
    }

    fun suggestAlternative(absentFacultyName: String, slots: List<TimetableSlot>, faculties: List<Faculty>): String {
        if (slots.isEmpty()) return "No timetable generated yet."
        val absent = faculties.firstOrNull { it.name.equals(absentFacultyName, true) }
            ?: return "Faculty not found."

        val target = slots.firstOrNull { it.faculty.id == absent.id }
            ?: return "No slot found for ${absent.name}."

        val candidateFaculty = faculties.filter {
            it.subject.equals(target.subject.name, true) && it.id != absent.id
        }

        for (faculty in candidateFaculty) {
            for (dayIndex in ScheduleConfig.days.indices) {
                val day = ScheduleConfig.days[dayIndex]
                if (!faculty.availability.contains(day)) continue

                for (periodIndex in 1..ScheduleConfig.periodsPerDay) {
                    val clash = slots.any {
                        it.day == day && it.period == periodIndex && it.faculty.id == faculty.id
                    }
                    if (!clash) {
                        return "Suggested: ${faculty.name} for ${target.subject.name} on $day period $periodIndex."
                    }
                }
            }
        }

        return "No safe alternative slot found for ${absent.name}."
    }
}
