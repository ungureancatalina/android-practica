package com.example.moviesxml

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_THEME = "theme"
        const val THEME_LIGHT = "light"
        const val THEME_DARK = "dark"
    }

    fun saveUsername(username: String) {
        prefs.edit().putString(KEY_USERNAME, username).apply()
    }

    fun getUsername(): String? = prefs.getString(KEY_USERNAME, null)

    fun saveTheme(theme: String) {
        prefs.edit().putString(KEY_THEME, theme).apply()
    }

    fun getTheme(): String = prefs.getString(KEY_THEME, THEME_LIGHT) ?: THEME_LIGHT
}
