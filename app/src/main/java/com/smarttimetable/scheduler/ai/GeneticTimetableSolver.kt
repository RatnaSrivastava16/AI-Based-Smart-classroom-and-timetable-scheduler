package com.smarttimetable.scheduler.ai

import com.smarttimetable.scheduler.data.Classroom
import com.smarttimetable.scheduler.data.Faculty
import com.smarttimetable.scheduler.data.Subject
import com.smarttimetable.scheduler.data.TimetableOption
import com.smarttimetable.scheduler.util.ScheduleConfig
import com.smarttimetable.scheduler.util.TimeSlot
import kotlin.math.max
import kotlin.random.Random

class GeneticTimetableSolver(
    private val faculties: List<Faculty>,
    private val subjects: List<Subject>,
    private val classrooms: List<Classroom>
) : TimetableSolver {

    override val name: String = "Genetic Algorithm"
    private val random = Random(System.currentTimeMillis())

    override fun generateTopTimetables(topN: Int): List<TimetableOption> {
        if (faculties.isEmpty() || subjects.isEmpty() || classrooms.isEmpty()) return emptyList()

        val populationSize = 30
        val generations = 60
        val requiredSessions = SolverCore.buildRequiredSessions(subjects, faculties)

        var population = List(populationSize) { buildRandomChromosome(requiredSessions) }

        repeat(generations) {
            val scored = population
                .map { chromosome -> chromosome to SolverCore.fitness(chromosome) }
                .sortedByDescending { it.second }

            val survivors = scored.take(max(2, (populationSize * 0.4).toInt())).map { it.first }

            val children = mutableListOf<List<SessionAssignment>>()
            while (survivors.size + children.size < populationSize) {
                val parentA = survivors.random(random)
                val parentB = survivors.random(random)
                val child = crossover(parentA, parentB)
                children.add(mutate(child))
            }
            population = survivors + children
        }

        return population
            .map { it to SolverCore.fitness(it) }
            .sortedByDescending { it.second }
            .take(topN)
            .mapIndexed { index, pair ->
                TimetableOption(
                    id = index + 1,
                    algorithmName = name,
                    fitnessScore = pair.second,
                    slots = SolverCore.toSlots(pair.first)
                )
            }
    }

    private fun buildRandomChromosome(sessions: List<Pair<Subject, Faculty>>): List<SessionAssignment> {
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

    private fun crossover(a: List<SessionAssignment>, b: List<SessionAssignment>): List<SessionAssignment> {
        val cut = a.size / 2
        return a.take(cut) + b.drop(cut)
    }

    private fun mutate(chromosome: List<SessionAssignment>): List<SessionAssignment> {
        return chromosome.map { assignment ->
            if (random.nextDouble() < 0.1) {
                assignment.copy(
                    timeSlot = if (random.nextBoolean()) {
                        TimeSlot(
                            dayIndex = random.nextInt(ScheduleConfig.days.size),
                            periodIndex = random.nextInt(ScheduleConfig.periodsPerDay)
                        )
                    } else assignment.timeSlot,
                    classroom = if (random.nextBoolean()) classrooms.random(random) else assignment.classroom
                )
            } else {
                assignment
            }
        }
    }
}
