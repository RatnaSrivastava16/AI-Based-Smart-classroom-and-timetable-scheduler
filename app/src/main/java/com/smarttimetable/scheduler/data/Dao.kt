package com.smarttimetable.scheduler.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaculty(faculty: FacultyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacultyList(faculties: List<FacultyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectList(subjects: List<SubjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroom(classroom: ClassroomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroomList(classrooms: List<ClassroomEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassGroup(classGroup: ClassGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassGroupList(classGroups: List<ClassGroupEntity>)

    @Query("SELECT * FROM faculty")
    suspend fun getAllFaculty(): List<FacultyEntity>

    @Query("SELECT * FROM subject")
    suspend fun getAllSubjects(): List<SubjectEntity>

    @Query("SELECT * FROM classroom")
    suspend fun getAllClassrooms(): List<ClassroomEntity>

    @Query("SELECT * FROM class_group")
    suspend fun getAllClassGroups(): List<ClassGroupEntity>

    @Query("DELETE FROM faculty WHERE id = :facultyId")
    suspend fun deleteFacultyById(facultyId: Int)

    @Query("DELETE FROM subject WHERE id = :subjectId")
    suspend fun deleteSubjectById(subjectId: Int)

    @Query("DELETE FROM classroom WHERE id = :classroomId")
    suspend fun deleteClassroomById(classroomId: Int)

    @Query("DELETE FROM class_group WHERE id = :classId")
    suspend fun deleteClassGroupById(classId: Int)

    @Query("DELETE FROM faculty")
    suspend fun clearFaculty()

    @Query("DELETE FROM subject")
    suspend fun clearSubjects()

    @Query("DELETE FROM classroom")
    suspend fun clearClassrooms()

    @Query("DELETE FROM class_group")
    suspend fun clearClassGroups()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetableEntries(entries: List<TimetableEntryEntity>)

    @Query("DELETE FROM timetable_entry")
    suspend fun clearTimetableEntries()

    @Query("SELECT * FROM timetable_entry ORDER BY orderIndex ASC")
    suspend fun getTimetableEntries(): List<TimetableEntryEntity>
}
