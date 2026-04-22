package com.smarttimetable.scheduler.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "faculty")
data class FacultyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val subject: String,
    val availabilityCsv: String,
    val avgLeavesPerMonth: Int
)

@Entity(tableName = "subject")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val targetClassName: String,
    val weeklyClassesRequired: Int
)

@Entity(tableName = "classroom")
data class ClassroomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val roomName: String,
    val capacity: Int
)

@Entity(tableName = "class_group")
data class ClassGroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val className: String,
    val sectionName: String,
    val strength: Int
)

@Entity(tableName = "timetable_entry")
data class TimetableEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderIndex: Int,
    val day: String,
    val period: Int,
    val subjectId: Int,
    val subjectName: String,
    val subjectTargetClassName: String,
    val facultyId: Int,
    val facultyName: String,
    val classroomId: Int,
    val classroomName: String,
    val classroomCapacity: Int,
    val conflict: Boolean
)
