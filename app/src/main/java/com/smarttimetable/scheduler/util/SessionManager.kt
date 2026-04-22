package com.smarttimetable.scheduler.util

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setOnboardingSeen(seen: Boolean) {
        prefs.edit().putBoolean(KEY_ONBOARDING_SEEN, seen).apply()
    }

    fun isOnboardingSeen(): Boolean {
        return prefs.getBoolean(KEY_ONBOARDING_SEEN, false)
    }

    fun setUserRole(role: String) {
        prefs.edit().putString(KEY_USER_ROLE, role).apply()
    }

    fun getUserRole(): String {
        return prefs.getString(KEY_USER_ROLE, "") ?: ""
    }

    fun setCollegeName(collegeName: String) {
        prefs.edit().putString(KEY_COLLEGE_NAME, collegeName.trim()).apply()
    }

    fun getCollegeName(): String {
        return prefs.getString(KEY_COLLEGE_NAME, "") ?: ""
    }

    fun getCollegeKey(): String {
        return getCollegeName()
            .trim()
            .lowercase()
            .replace(Regex("[^a-z0-9]+"), "-")
            .trim('-')
    }

    fun setUserIdentity(identity: String) {
        prefs.edit().putString(KEY_USER_IDENTITY, identity.trim()).apply()
    }

    fun getUserIdentity(): String {
        return prefs.getString(KEY_USER_IDENTITY, "") ?: ""
    }

    fun setFacultyName(name: String) {
        prefs.edit().putString(KEY_FACULTY_NAME, name.trim()).apply()
    }

    fun getFacultyName(): String {
        return prefs.getString(KEY_FACULTY_NAME, "") ?: ""
    }

    fun clearSession() {
        prefs.edit()
            .remove(KEY_USER_ROLE)
            .remove(KEY_COLLEGE_NAME)
            .remove(KEY_USER_IDENTITY)
            .remove(KEY_FACULTY_NAME)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "ai_scheduler_session"
        private const val KEY_ONBOARDING_SEEN = "onboarding_seen"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_COLLEGE_NAME = "college_name"
        private const val KEY_USER_IDENTITY = "user_identity"
        private const val KEY_FACULTY_NAME = "faculty_name"

        const val ROLE_ADMIN = "admin"
        const val ROLE_FACULTY = "faculty"
    }
}
