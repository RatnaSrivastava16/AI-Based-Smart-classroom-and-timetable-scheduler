package com.smarttimetable.scheduler.ai;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0002\u0010\tJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0018\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u00130\u0003H\u0002J*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/smarttimetable/scheduler/ai/GeneticTimetableSolver;", "Lcom/smarttimetable/scheduler/ai/TimetableSolver;", "faculties", "", "Lcom/smarttimetable/scheduler/data/Faculty;", "subjects", "Lcom/smarttimetable/scheduler/data/Subject;", "classrooms", "Lcom/smarttimetable/scheduler/data/Classroom;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "name", "", "getName", "()Ljava/lang/String;", "random", "Lkotlin/random/Random;", "buildRandomChromosome", "Lcom/smarttimetable/scheduler/ai/SessionAssignment;", "sessions", "Lkotlin/Pair;", "crossover", "a", "b", "generateTopTimetables", "Lcom/smarttimetable/scheduler/data/TimetableOption;", "topN", "", "mutate", "chromosome", "app_debug"})
public final class GeneticTimetableSolver implements com.smarttimetable.scheduler.ai.TimetableSolver {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Faculty> faculties = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Subject> subjects = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.smarttimetable.scheduler.data.Classroom> classrooms = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = "Genetic Algorithm";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.random.Random random = null;
    
    public GeneticTimetableSolver(@org.jetbrains.annotations.NotNull()
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
    
    private final java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> buildRandomChromosome(java.util.List<kotlin.Pair<com.smarttimetable.scheduler.data.Subject, com.smarttimetable.scheduler.data.Faculty>> sessions) {
        return null;
    }
    
    private final java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> crossover(java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> a, java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> b) {
        return null;
    }
    
    private final java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> mutate(java.util.List<com.smarttimetable.scheduler.ai.SessionAssignment> chromosome) {
        return null;
    }
}