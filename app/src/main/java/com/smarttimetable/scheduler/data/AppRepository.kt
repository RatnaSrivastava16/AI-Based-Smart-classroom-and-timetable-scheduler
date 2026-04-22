package com.smarttimetable.scheduler.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AppRepository(
    private val dao: AppDao,
    private val collegeKey: String,
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun addFaculty(faculty: FacultyEntity) {
        dao.insertFaculty(faculty)
        syncToCloud()
    }

    suspend fun addSubject(subject: SubjectEntity) {
        dao.insertSubject(subject)
        syncToCloud()
    }

    suspend fun addClassroom(classroom: ClassroomEntity) {
        dao.insertClassroom(classroom)
        syncToCloud()
    }

    suspend fun addClassGroup(classGroup: ClassGroupEntity) {
        dao.insertClassGroup(classGroup)
        syncToCloud()
    }

    suspend fun getFaculty() = dao.getAllFaculty()
    suspend fun getSubjects() = dao.getAllSubjects()
    suspend fun getClassrooms() = dao.getAllClassrooms()
    suspend fun getClassGroups() = dao.getAllClassGroups()

    suspend fun deleteFaculty(id: Int) {
        dao.deleteFacultyById(id)
        syncToCloud()
    }

    suspend fun deleteSubject(id: Int) {
        dao.deleteSubjectById(id)
        syncToCloud()
    }

    suspend fun deleteClassroom(id: Int) {
        dao.deleteClassroomById(id)
        syncToCloud()
    }

    suspend fun deleteClassGroup(id: Int) {
        dao.deleteClassGroupById(id)
        syncToCloud()
    }

    suspend fun saveTimetableEntries(entries: List<TimetableEntryEntity>) {
        dao.clearTimetableEntries()
        if (entries.isNotEmpty()) {
            dao.insertTimetableEntries(entries)
        }
        syncToCloud()
    }

    suspend fun getTimetableEntries() = dao.getTimetableEntries()

    suspend fun syncFromCloud() {
        if (collegeKey.isBlank()) return
        val snapshot = try {
            firestore.collection("colleges").document(collegeKey)
                .collection("scheduler").document("data")
                .get()
                .awaitResult()
        } catch (_: Exception) {
            return
        }

        if (!snapshot.exists()) return

        val faculties = (snapshot.get("faculties") as? List<Map<String, Any?>>).orEmpty().mapIndexed { index, item ->
            FacultyEntity(
                id = (item["id"] as? Number)?.toInt() ?: index + 1,
                name = item["name"] as? String ?: "",
                subject = item["subject"] as? String ?: "",
                availabilityCsv = item["availabilityCsv"] as? String ?: "",
                avgLeavesPerMonth = (item["avgLeavesPerMonth"] as? Number)?.toInt() ?: 0
            )
        }
        val subjects = (snapshot.get("subjects") as? List<Map<String, Any?>>).orEmpty().mapIndexed { index, item ->
            SubjectEntity(
                id = (item["id"] as? Number)?.toInt() ?: index + 1,
                name = item["name"] as? String ?: "",
                targetClassName = item["targetClassName"] as? String ?: "",
                weeklyClassesRequired = (item["weeklyClassesRequired"] as? Number)?.toInt() ?: 0
            )
        }
        val classrooms = (snapshot.get("classrooms") as? List<Map<String, Any?>>).orEmpty().mapIndexed { index, item ->
            ClassroomEntity(
                id = (item["id"] as? Number)?.toInt() ?: index + 1,
                roomName = item["roomName"] as? String ?: "",
                capacity = (item["capacity"] as? Number)?.toInt() ?: 0
            )
        }
        val classGroups = (snapshot.get("classGroups") as? List<Map<String, Any?>>).orEmpty().mapIndexed { index, item ->
            ClassGroupEntity(
                id = (item["id"] as? Number)?.toInt() ?: index + 1,
                className = item["className"] as? String ?: "",
                sectionName = item["sectionName"] as? String ?: "",
                strength = (item["strength"] as? Number)?.toInt() ?: 0
            )
        }
        val timetable = (snapshot.get("timetableEntries") as? List<Map<String, Any?>>).orEmpty().mapIndexed { index, item ->
            TimetableEntryEntity(
                id = (item["id"] as? Number)?.toInt() ?: index + 1,
                orderIndex = (item["orderIndex"] as? Number)?.toInt() ?: index,
                day = item["day"] as? String ?: "",
                period = (item["period"] as? Number)?.toInt() ?: 0,
                subjectId = (item["subjectId"] as? Number)?.toInt() ?: 0,
                subjectName = item["subjectName"] as? String ?: "",
                subjectTargetClassName = item["subjectTargetClassName"] as? String ?: "",
                facultyId = (item["facultyId"] as? Number)?.toInt() ?: 0,
                facultyName = item["facultyName"] as? String ?: "",
                classroomId = (item["classroomId"] as? Number)?.toInt() ?: 0,
                classroomName = item["classroomName"] as? String ?: "",
                classroomCapacity = (item["classroomCapacity"] as? Number)?.toInt() ?: 0,
                conflict = item["conflict"] as? Boolean ?: false
            )
        }

        dao.clearFaculty()
        dao.clearSubjects()
        dao.clearClassrooms()
        dao.clearClassGroups()
        dao.clearTimetableEntries()
        if (faculties.isNotEmpty()) dao.insertFacultyList(faculties)
        if (subjects.isNotEmpty()) dao.insertSubjectList(subjects)
        if (classrooms.isNotEmpty()) dao.insertClassroomList(classrooms)
        if (classGroups.isNotEmpty()) dao.insertClassGroupList(classGroups)
        if (timetable.isNotEmpty()) dao.insertTimetableEntries(timetable)
    }

    private suspend fun syncToCloud() {
        if (collegeKey.isBlank()) return
        val payload = hashMapOf(
            "faculties" to dao.getAllFaculty().map {
                mapOf(
                    "id" to it.id,
                    "name" to it.name,
                    "subject" to it.subject,
                    "availabilityCsv" to it.availabilityCsv,
                    "avgLeavesPerMonth" to it.avgLeavesPerMonth
                )
            },
            "subjects" to dao.getAllSubjects().map {
                mapOf(
                    "id" to it.id,
                    "name" to it.name,
                    "targetClassName" to it.targetClassName,
                    "weeklyClassesRequired" to it.weeklyClassesRequired
                )
            },
            "classrooms" to dao.getAllClassrooms().map {
                mapOf(
                    "id" to it.id,
                    "roomName" to it.roomName,
                    "capacity" to it.capacity
                )
            },
            "classGroups" to dao.getAllClassGroups().map {
                mapOf(
                    "id" to it.id,
                    "className" to it.className,
                    "sectionName" to it.sectionName,
                    "strength" to it.strength
                )
            },
            "timetableEntries" to dao.getTimetableEntries().map {
                mapOf(
                    "id" to it.id,
                    "orderIndex" to it.orderIndex,
                    "day" to it.day,
                    "period" to it.period,
                    "subjectId" to it.subjectId,
                    "subjectName" to it.subjectName,
                    "subjectTargetClassName" to it.subjectTargetClassName,
                    "facultyId" to it.facultyId,
                    "facultyName" to it.facultyName,
                    "classroomId" to it.classroomId,
                    "classroomName" to it.classroomName,
                    "classroomCapacity" to it.classroomCapacity,
                    "conflict" to it.conflict
                )
            },
            "updatedAt" to System.currentTimeMillis()
        )

        try {
            firestore.collection("colleges").document(collegeKey)
                .collection("scheduler").document("data")
                .set(payload)
                .awaitResult()
        } catch (_: Exception) {
            // Keep local data even if cloud sync is temporarily unavailable.
        }
    }
}

private suspend fun <T> Task<T>.awaitResult(): T = suspendCancellableCoroutine { continuation ->
    addOnSuccessListener { result -> continuation.resume(result) }
    addOnFailureListener { error -> continuation.resumeWithException(error) }
    addOnCanceledListener { continuation.cancel() }
}
