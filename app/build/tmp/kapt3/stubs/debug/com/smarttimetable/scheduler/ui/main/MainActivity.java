package com.smarttimetable.scheduler.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u0012\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0014\u0010\u001e\u001a\u00020\u00182\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002J\b\u0010!\u001a\u00020\u0018H\u0002J*\u0010\"\u001a\u00020\u00182 \u0010#\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0006\u0012\u0004\u0018\u00010'0%0$H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020\u0018H\u0002J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006,"}, d2 = {"Lcom/smarttimetable/scheduler/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/smarttimetable/scheduler/ui/main/TimetableAdapter;", "binding", "Lcom/smarttimetable/scheduler/databinding/ActivityMainBinding;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getFirestore", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "firestore$delegate", "Lkotlin/Lazy;", "sessionManager", "Lcom/smarttimetable/scheduler/util/SessionManager;", "getSessionManager", "()Lcom/smarttimetable/scheduler/util/SessionManager;", "sessionManager$delegate", "viewModel", "Lcom/smarttimetable/scheduler/ui/main/MainViewModel;", "getViewModel", "()Lcom/smarttimetable/scheduler/ui/main/MainViewModel;", "viewModel$delegate", "ensureCollegeConfigured", "", "initializeDashboard", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openPage", "target", "Ljava/lang/Class;", "promptCollegeName", "renderFacultyPreview", "items", "", "Lkotlin/Triple;", "", "Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardActions;", "setupActions", "setupNavigation", "setupRecycler", "setupSessionCard", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.smarttimetable.scheduler.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.smarttimetable.scheduler.ui.main.TimetableAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy sessionManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy firestore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.smarttimetable.scheduler.util.SessionManager getSessionManager() {
        return null;
    }
    
    private final com.google.firebase.firestore.FirebaseFirestore getFirestore() {
        return null;
    }
    
    private final com.smarttimetable.scheduler.ui.main.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupSessionCard() {
    }
    
    private final void setupRecycler() {
    }
    
    private final void setupActions() {
    }
    
    private final void setupNavigation() {
    }
    
    private final void ensureCollegeConfigured() {
    }
    
    private final void initializeDashboard() {
    }
    
    private final void promptCollegeName() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void renderFacultyPreview(java.util.List<kotlin.Triple<java.lang.String, java.lang.String, com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardActions>> items) {
    }
    
    private final void openPage(java.lang.Class<?> target) {
    }
}