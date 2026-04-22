package com.smarttimetable.scheduler.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003J\t\u0010 \u001a\u00020\rH\u00c6\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0005H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006&"}, d2 = {"Lcom/smarttimetable/scheduler/data/TimetableSlot;", "", "day", "", "period", "", "subject", "Lcom/smarttimetable/scheduler/data/Subject;", "faculty", "Lcom/smarttimetable/scheduler/data/Faculty;", "classroom", "Lcom/smarttimetable/scheduler/data/Classroom;", "conflict", "", "(Ljava/lang/String;ILcom/smarttimetable/scheduler/data/Subject;Lcom/smarttimetable/scheduler/data/Faculty;Lcom/smarttimetable/scheduler/data/Classroom;Z)V", "getClassroom", "()Lcom/smarttimetable/scheduler/data/Classroom;", "getConflict", "()Z", "getDay", "()Ljava/lang/String;", "getFaculty", "()Lcom/smarttimetable/scheduler/data/Faculty;", "getPeriod", "()I", "getSubject", "()Lcom/smarttimetable/scheduler/data/Subject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class TimetableSlot {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String day = null;
    private final int period = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.smarttimetable.scheduler.data.Subject subject = null;
    @org.jetbrains.annotations.NotNull()
    private final com.smarttimetable.scheduler.data.Faculty faculty = null;
    @org.jetbrains.annotations.NotNull()
    private final com.smarttimetable.scheduler.data.Classroom classroom = null;
    private final boolean conflict = false;
    
    public TimetableSlot(@org.jetbrains.annotations.NotNull()
    java.lang.String day, int period, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Subject subject, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Faculty faculty, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Classroom classroom, boolean conflict) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDay() {
        return null;
    }
    
    public final int getPeriod() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Subject getSubject() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Faculty getFaculty() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Classroom getClassroom() {
        return null;
    }
    
    public final boolean getConflict() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Subject component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Faculty component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.Classroom component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smarttimetable.scheduler.data.TimetableSlot copy(@org.jetbrains.annotations.NotNull()
    java.lang.String day, int period, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Subject subject, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Faculty faculty, @org.jetbrains.annotations.NotNull()
    com.smarttimetable.scheduler.data.Classroom classroom, boolean conflict) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}