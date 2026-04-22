package com.smarttimetable.scheduler.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\u0012\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006$"}, d2 = {"Lcom/smarttimetable/scheduler/ui/login/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/smarttimetable/scheduler/databinding/ActivityLoginBinding;", "googleLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "sessionManager", "Lcom/smarttimetable/scheduler/util/SessionManager;", "viewModel", "Lcom/smarttimetable/scheduler/ui/login/LoginViewModel;", "getViewModel", "()Lcom/smarttimetable/scheduler/ui/login/LoginViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyEntryAnimations", "", "bindClickListeners", "getGoogleWebClientId", "", "navigateByRole", "role", "email", "collegeName", "facultyName", "observeState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "selectedRole", "setButtonsEnabled", "enabled", "", "setupRoleUi", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.smarttimetable.scheduler.databinding.ActivityLoginBinding binding;
    private com.smarttimetable.scheduler.util.SessionManager sessionManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleLauncher = null;
    
    public LoginActivity() {
        super();
    }
    
    private final com.smarttimetable.scheduler.ui.login.LoginViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void bindClickListeners() {
    }
    
    private final void observeState() {
    }
    
    private final void navigateByRole(java.lang.String role, java.lang.String email, java.lang.String collegeName, java.lang.String facultyName) {
    }
    
    private final java.lang.String selectedRole() {
        return null;
    }
    
    private final void setButtonsEnabled(boolean enabled) {
    }
    
    private final void setupRoleUi() {
    }
    
    private final void applyEntryAnimations() {
    }
    
    private final java.lang.String getGoogleWebClientId() {
        return null;
    }
}