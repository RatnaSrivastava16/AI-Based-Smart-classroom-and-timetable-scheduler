package com.smarttimetable.scheduler.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0011J\u000e\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u000bJ\u000e\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/smarttimetable/scheduler/util/SessionManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearSession", "", "getCollegeKey", "", "getCollegeName", "getFacultyName", "getUserIdentity", "getUserRole", "isOnboardingSeen", "", "setCollegeName", "collegeName", "setFacultyName", "name", "setOnboardingSeen", "seen", "setUserIdentity", "identity", "setUserRole", "role", "Companion", "app_debug"})
public final class SessionManager {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "ai_scheduler_session";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ONBOARDING_SEEN = "onboarding_seen";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_ROLE = "user_role";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_COLLEGE_NAME = "college_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_IDENTITY = "user_identity";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FACULTY_NAME = "faculty_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ROLE_ADMIN = "admin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ROLE_FACULTY = "faculty";
    @org.jetbrains.annotations.NotNull()
    public static final com.smarttimetable.scheduler.util.SessionManager.Companion Companion = null;
    
    public SessionManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void setOnboardingSeen(boolean seen) {
    }
    
    public final boolean isOnboardingSeen() {
        return false;
    }
    
    public final void setUserRole(@org.jetbrains.annotations.NotNull()
    java.lang.String role) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserRole() {
        return null;
    }
    
    public final void setCollegeName(@org.jetbrains.annotations.NotNull()
    java.lang.String collegeName) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCollegeName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCollegeKey() {
        return null;
    }
    
    public final void setUserIdentity(@org.jetbrains.annotations.NotNull()
    java.lang.String identity) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserIdentity() {
        return null;
    }
    
    public final void setFacultyName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFacultyName() {
        return null;
    }
    
    public final void clearSession() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/smarttimetable/scheduler/util/SessionManager$Companion;", "", "()V", "KEY_COLLEGE_NAME", "", "KEY_FACULTY_NAME", "KEY_ONBOARDING_SEEN", "KEY_USER_IDENTITY", "KEY_USER_ROLE", "PREFS_NAME", "ROLE_ADMIN", "ROLE_FACULTY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}