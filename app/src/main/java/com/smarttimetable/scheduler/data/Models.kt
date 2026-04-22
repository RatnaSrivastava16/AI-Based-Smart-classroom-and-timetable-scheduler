package com.smarttimetable.scheduler.data

import com.smarttimetable.scheduler.util.TimeSlot

data class Faculty(
    val id: Int,
    val name: String,
    val subject: String,
    val availability: Set<String>,
    val avgLeavesPerMonth: Int
)

data class Subject(
    val id: Int,
    val name: String,
    val targetClassName: String,
    val weeklyClassesRequired: Int
)

data class Classroom(
    val id: Int,
    val roomName: String,
    val capacity: Int
)

data class ClassGroup(
    val id: Int,
    val className: String,
    val sectionName: String,
    val strength: Int
)

data class TimetableSlot(
    val day: String,
    val period: Int,
    val subject: Subject,
    val faculty: Faculty,
    val classroom: Classroom,
    val conflict: Boolean
)

data class TimetableOption(
    val id: Int,
    val algorithmName: String,
    val fitnessScore: Double,
    val slots: List<TimetableSlot>
)

data class SolverBenchmark(
    val algorithm: String,
    val timeTakenMs: Long,
    val conflicts: Int,
    val fitnessScore: Double
)

data class DashboardStats(
    val facultyCount: Int,
    val subjectCount: Int,
    val classCount: Int,
    val classroomCount: Int,
    val timetableCount: Int,
    val conflictCount: Int,
    val totalCapacity: Int
)

data class DashboardSnapshot(
    val stats: DashboardStats,
    val faculties: List<Faculty>,
    val subjects: List<Subject>,
    val classGroups: List<ClassGroup>,
    val classrooms: List<Classroom>,
    val timetableSlots: List<TimetableSlot>
)

data class DisplayRow(
    val type: RowType,
    val header: String = "",
    val fitness: String = "",
    val slot: TimetableSlot? = null,
    val slotIndex: Int = -1
)

enum class RowType {
    HEADER,
    SLOT
}

fun FacultyEntity.toModel(): Faculty {
    val availability = parseAvailabilityDays(availabilityCsv)

    return Faculty(
        id = id,
        name = name,
        subject = subject,
        availability = availability,
        avgLeavesPerMonth = avgLeavesPerMonth
    )
}

fun SubjectEntity.toModel() = Subject(id, name, targetClassName, weeklyClassesRequired)
fun ClassroomEntity.toModel() = Classroom(id, roomName, capacity)
fun ClassGroupEntity.toModel() = ClassGroup(id, className, sectionName, strength)

fun TimetableSlot.toEntity(orderIndex: Int): TimetableEntryEntity {
    return TimetableEntryEntity(
        orderIndex = orderIndex,
        day = day,
        period = period,
        subjectId = subject.id,
        subjectName = subject.name,
        subjectTargetClassName = subject.targetClassName,
        facultyId = faculty.id,
        facultyName = faculty.name,
        classroomId = classroom.id,
        classroomName = classroom.roomName,
        classroomCapacity = classroom.capacity,
        conflict = conflict
    )
}

fun TimetableEntryEntity.toModel(subjectsById: Map<Int, SubjectEntity> = emptyMap()): TimetableSlot {
    val resolvedClassName = when {
        subjectTargetClassName.isNotBlank() -> subjectTargetClassName
        else -> subjectsById[subjectId]?.targetClassName.orEmpty()
    }
    return TimetableSlot(
        day = day,
        period = period,
        subject = Subject(
            id = subjectId,
            name = subjectName,
            targetClassName = resolvedClassName,
            weeklyClassesRequired = subjectsById[subjectId]?.weeklyClassesRequired ?: 0
        ),
        faculty = Faculty(
            id = facultyId,
            name = facultyName,
            subject = subjectName,
            availability = emptySet(),
            avgLeavesPerMonth = 0
        ),
        classroom = Classroom(id = classroomId, roomName = classroomName, capacity = classroomCapacity),
        conflict = conflict
    )
}

fun TimeSlot.toDayName(): String = when (dayIndex) {
    0 -> "Mon"
    1 -> "Tue"
    2 -> "Wed"
    3 -> "Thu"
    else -> "Fri"
}

private fun parseAvailabilityDays(raw: String): Set<String> {
    val normalized = raw
        .replace("/", ",")
        .replace(";", ",")
        .replace("|", ",")
        .split(",", " ", "\n", "\t")
        .mapNotNull { normalizeDayToken(it) }
        .toSet()

    // If no valid day token is found, default to all working days.
    return if (normalized.isEmpty()) setOf("Mon", "Tue", "Wed", "Thu", "Fri") else normalized
}

private fun normalizeDayToken(token: String): String? {
    return when (token.trim().lowercase()) {
        "mon", "monday" -> "Mon"
        "tue", "tues", "tuesday" -> "Tue"
        "wed", "wednesday" -> "Wed"
        "thu", "thur", "thurs", "thursday" -> "Thu"
        "fri", "friday" -> "Fri"
        else -> null
    }
}
