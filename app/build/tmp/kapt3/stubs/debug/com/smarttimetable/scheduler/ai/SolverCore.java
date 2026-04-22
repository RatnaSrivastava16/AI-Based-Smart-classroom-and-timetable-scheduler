package com.smarttimetable.scheduler.ai;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0004H\u0002J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0004J*\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004J\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a8\u0006\u0018"}, d2 = {"Lcom/smarttimetable/scheduler/ai/SolverCore;", "", "()V", "buildRequiredSessions", "", "Lkotlin/Pair;", "Lcom/smarttimetable/scheduler/data/Subject;", "Lcom/smarttimetable/scheduler/data/Faculty;", "subjects", "faculties", "conflictKey", "", "assignment", "Lcom/smarttimetable/scheduler/ai/SessionAssignment;", "detectConflictKeys", "", "assignments", "fitness", "", "suggestAlternative", "absentFacultyName", "slots", "Lcom/smarttimetable/scheduler/data/TimetableSlot;", "toSlots", "app_debug"})
public final class SolverCore {
    @org.jetbrains.annotations.NotNull()
    public static final com.smarttimetable.scheduler.ai.SolverCore INSTANCE = null;
    
    private SolverCore() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<com.smarttimetable.scheduler.data.Subject, com.smarttimetable.scheduler.data.Faculty>> buildRequiredSessions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Subject> subjects, @org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Faculty> faculties) {
        return null;
    }
    
    public final double fitness(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> assignments) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.smarttimetable.scheduler.data.TimetableSlot> toSlots(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> assignments) {
        return null;
    }
    
    private final java.util.Set<java.lang.String> detectConflictKeys(java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> assignments) {
        return null;
    }
    
    private final java.lang.String conflictKey(com.smarttimetable.scheduler.ai.SessionAssignment assignment) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String suggestAlternative(@org.jetbrains.annotations.NotNull()
    java.lang.String absentFacultyName, @org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.TimetableSlot> slots, @org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Faculty> faculties) {
        return null;
    }
}