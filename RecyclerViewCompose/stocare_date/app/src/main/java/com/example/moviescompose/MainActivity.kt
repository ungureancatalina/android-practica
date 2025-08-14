package com.example.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescompose.ui.LoginScreen
import com.example.moviescompose.ui.MoviesListScreen
import com.example.moviescompose.ui.MoviesTheme
import com.example.moviescompose.ui.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferencesManager(this)
        val isDark = prefs.getTheme() == PreferencesManager.THEME_DARK
        setContent { AppRoot(isDarkStart = isDark, prefs = prefs) }
    }
}

@Composable
private fun AppRoot(isDarkStart: Boolean, prefs: PreferencesManager) {
    var darkTheme by remember { mutableStateOf(isDarkStart) }
    MoviesTheme(darkTheme = darkTheme) {
        Surface {
            val nav = rememberNavController()
            NavHost(navController = nav, startDestination = Routes.Login) {
                composable(Routes.Login) {
                    LoginScreen(prefs = prefs) {
                        nav.navigate(Routes.List) { popUpTo(Routes.Login) { inclusive = true } }
                    }
                }
                composable(Routes.List) {
                    MoviesListScreen(onOpenSettings = { nav.navigate(Routes.Settings) })
                }
                composable(Routes.Settings) {
                    SettingsScreen(
                        prefs = prefs,
                        darkTheme = darkTheme,
                        onToggleDark = { enabled ->
                            darkTheme = enabled
                            prefs.saveTheme(if (enabled) PreferencesManager.THEME_DARK else PreferencesManager.THEME_LIGHT)
                        },
                        onBack = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}