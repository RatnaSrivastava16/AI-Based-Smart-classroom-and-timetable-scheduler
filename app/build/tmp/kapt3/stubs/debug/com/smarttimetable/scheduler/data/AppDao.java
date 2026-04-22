package com.smarttimetable.scheduler.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010 J\u001c\u0010!\u001a\u00020\u00032\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010&J\u001c\u0010'\u001a\u00020\u00032\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010+J\u001c\u0010,\u001a\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001bH\u00a7@\u00a2\u0006\u0002\u00100J\u001c\u00101\u001a\u00020\u00032\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014H\u00a7@\u00a2\u0006\u0002\u0010#J\u001c\u00103\u001a\u00020\u00032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0014H\u00a7@\u00a2\u0006\u0002\u0010#\u00a8\u00065"}, d2 = {"Lcom/smarttimetable/scheduler/data/AppDao;", "", "clearClassGroups", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearClassrooms", "clearFaculty", "clearSubjects", "clearTimetableEntries", "deleteClassGroupById", "classId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteClassroomById", "classroomId", "deleteFacultyById", "facultyId", "deleteSubjectById", "subjectId", "getAllClassGroups", "", "Lcom/smarttimetable/scheduler/data/ClassGroupEntity;", "getAllClassrooms", "Lcom/smarttimetable/scheduler/data/ClassroomEntity;", "getAllFaculty", "Lcom/smarttimetable/scheduler/data/FacultyEntity;", "getAllSubjects", "Lcom/smarttimetable/scheduler/data/SubjectEntity;", "getTimetableEntries", "Lcom/smarttimetable/scheduler/data/TimetableEntryEntity;", "insertClassGroup", "classGroup", "(Lcom/smarttimetable/scheduler/data/ClassGroupEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertClassGroupList", "classGroups", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertClassroom", "classroom", "(Lcom/smarttimetable/scheduler/data/ClassroomEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertClassroomList", "classrooms", "insertFaculty", "faculty", "(Lcom/smarttimetable/scheduler/data/FacultyEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertFacultyList", "faculties", "insertSubject", "subject", "(Lcom/smarttimetable/scheduler/data/SubjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSubjectList", "subjects", "insertTimetableEntries", "entries", "app_debug"})
@androidx.room.Dao()
public abstract interface AppDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFaculty(@org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.FacultyEntity faculty, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFacultyList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.FacultyEntity> faculties, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSubject(@org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.SubjectEntity subject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSubjectList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.SubjectEntity> subjects, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertClassroom(@org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.ClassroomEntity classroom, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertClassroomList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.ClassroomEntity> classrooms, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertClassGroup(@org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.ClassGroupEntity classGroup, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertClassGroupList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.ClassGroupEntity> classGroups, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM faculty")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllFaculty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.smarttimetable.scheduler.data.FacultyEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM subject")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSubjects(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.smarttimetable.scheduler.data.SubjectEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM classroom")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllClassrooms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.smarttimetable.scheduler.data.ClassroomEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM class_group")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllClassGroups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.smarttimetable.scheduler.data.ClassGroupEntity>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM faculty WHERE id = :facultyId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFacultyById(int facultyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM subject WHERE id = :subjectId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSubjectById(int subjectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM classroom WHERE id = :classroomId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteClassroomById(int classroomId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM class_group WHERE id = :classId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteClassGroupById(int classId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM faculty")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearFaculty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM subject")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearSubjects(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM classroom")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearClassrooms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM class_group")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearClassGroups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTimetableEntries(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.TimetableEntryEntity> entries, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM timetable_entry")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearTimetableEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM timetable_entry ORDER BY orderIndex ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTimetableEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.smarttimetable.scheduler.data.TimetableEntryEntity>> $completion);
}