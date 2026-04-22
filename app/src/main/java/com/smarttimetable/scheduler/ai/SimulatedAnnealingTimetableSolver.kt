package com.smarttimetable.scheduler.ai

import com.smarttimetable.scheduler.data.Classroom
import com.smarttimetable.scheduler.data.Faculty
import com.smarttimetable.scheduler.data.Subject
import com.smarttimetable.scheduler.data.TimetableOption
import com.smarttimetable.scheduler.util.ScheduleConfig
import com.smarttimetable.scheduler.util.TimeSlot
import kotlin.math.exp
import kotlin.random.Random

class SimulatedAnnealingTimetableSolver(
    private val faculties: List<Faculty>,
    private val subjects: List<Subject>,
    private val classrooms: List<Classroom>
) : TimetableSolver {

    override val name: String = "Simulated Annealing"
    private val random = Random(System.currentTimeMillis())

    override fun generateTopTimetables(topN: Int): List<TimetableOption> {
        if (faculties.isEmpty() || subjects.isEmpty() || classrooms.isEmpty()) return emptyList()

        val sessions = SolverCore.buildRequiredSessions(subjects, faculties)
        var current = randomAssignments(sessions)
        var currentFitness = SolverCore.fitness(current)

        var best = current
        var bestFitness = currentFitness

        var temperature = 100.0
        val coolingRate = 0.95

        repeat(120) {
            val candidate = neighbor(current)
            val candidateFitness = SolverCore.fitness(candidate)

            if (accept(currentFitness, candidateFitness, temperature)) {
                current = candidate
                currentFitness = candidateFitness
            }

            if (candidateFitness > bestFitness) {
                best = candidate
                bestFitness = candidateFitness
            }

            temperature *= coolingRate
        }

        return listOf(
            TimetableOption(
                id = 1,
                algorithmName = name,
                fitnessScore = bestFitness,
                slots = SolverCore.toSlots(best)
            )
        )
    }

    private fun randomAssignments(sessions: List<Pair<Subject, Faculty>>): List<SessionAssignment> {
        return sessions.map { (subject, faculty) ->
            SessionAssignment(
                subject = subject,
                faculty = faculty,
                timeSlot = TimeSlot(
                    dayIndex = random.nextInt(ScheduleConfig.days.size),
                    periodIndex = random.nextInt(ScheduleConfig.periodsPerDay)
                ),
                classroom = classrooms.random(random)
            )
        }
    }

    private fun neighbor(assignments: List<SessionAssignment>): List<SessionAssignment> {
        if (assignments.isEmpty()) return assignments
        val mutable = assignments.toMutableList()
        val index = random.nextInt(mutable.size)
        val base = mutable[index]
        mutable[index] = base.copy(
            timeSlot = TimeSlot(
                dayIndex = random.nextInt(ScheduleConfig.days.size),
                periodIndex = random.nextInt(ScheduleConfig.periodsPerDay)
            ),
            classroom = classrooms.random(random)
        )
        return mutable
    }

    private fun accept(currentFitness: Double, candidateFitness: Double, temperature: Double): Boolean {
        if (candidateFitness >= currentFitness) return true
        if (temperature <= 0.001) return false
        val probability = exp((candidateFitness - currentFitness) / temperature)
        return random.nextDouble() < probability
    }
}
