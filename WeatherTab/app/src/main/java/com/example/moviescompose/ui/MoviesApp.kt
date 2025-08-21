package com.example.moviescompose.ui

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.moviescompose.data.PreferencesManager

@Composable
fun MoviesApp() {
    val context = LocalContext.current

    // initializam PreferencesManager pentru salvarea user + tema
    val prefs = remember { PreferencesManager(context.applicationContext) }

    // citim tema salvata si o tinem in stare Compose
    var darkTheme by remember {
        mutableStateOf(prefs.getTheme() == PreferencesManager.THEME_DARK)
    }

    // controller pentru navigatie
    val navController = rememberNavController()

    // aplicam tema custom a aplicatiei
    MoviesTheme(darkTheme = darkTheme) {
        AppScaffold(
            navController = navController,
            darkTheme = darkTheme,
            onToggleDark = { isDark ->
                // schimbam tema si o salvam in preferinte
                darkTheme = isDark
                prefs.saveTheme(
                    if (isDark) PreferencesManager.THEME_DARK
                    else PreferencesManager.THEME_LIGHT
                )
            },
            prefs = prefs
        )
    }
}
