package com.smarttimetable.scheduler.ai;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/smarttimetable/scheduler/ai/GreedyTimetableSolver;", "Lcom/smarttimetable/scheduler/ai/TimetableSolver;", "faculties", "", "Lcom/smarttimetable/scheduler/data/Faculty;", "subjects", "Lcom/smarttimetable/scheduler/data/Subject;", "classrooms", "Lcom/smarttimetable/scheduler/data/Classroom;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "name", "", "getName", "()Ljava/lang/String;", "generateTopTimetables", "Lcom/smarttimetable/scheduler/data/TimetableOption;", "topN", "", "app_debug"})
public final class GreedyTimetableSolver implements com.smarttimetable.scheduler.ai.TimetableSolver {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Faculty> faculties = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Subject> subjects = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Classroom> classrooms = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = "Greedy";
    
    public GreedyTimetableSolver(@org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Faculty> faculties, @org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Subject> subjects, @org.jetbrains.annotations.NotNull()
    java.util.List<com.smarttimetable.scheduler.data.Classroom> classrooms) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.smarttimetable.scheduler.data.TimetableOption> generateTopTimetables(int topN) {
        return null;
    }
}