package com.example.moviescompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.moviescompose.data.PreferencesManager

@Composable
fun AppScaffold(
    navController: NavHostController,
    darkTheme: Boolean,
    onToggleDark: (Boolean) -> Unit,
    prefs: PreferencesManager
) {
    // definim tab-urile din bara de jos
    val tabs = listOf(Route.Movies, Route.Weather, Route.Settings)

    Scaffold(
        bottomBar = {

            // extragem ruta curenta pentru a marca butonul selectat
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStack?.destination?.route

            // bara de jos nu se afiseaza pe ecranul de login
            if (currentRoute != Route.Login.path) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    // desenam fiecare buton de tab
                    tabs.forEach { route ->
                        val selected = currentRoute == route.path
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                // navigam catre ruta corespunzatoare
                                navController.navigate(route.path) {
                                    launchSingleTop = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            },
                            icon = {
                                when (route) {
                                    // icon diferit pentru fiecare ruta
                                    Route.Movies -> Icon(Icons.Filled.Movie, contentDescription = route.label)
                                    Route.Weather -> Icon(Icons.Filled.Cloud, contentDescription = route.label)
                                    Route.Settings -> Icon(Icons.Filled.Settings, contentDescription = route.label)
                                    Route.Login -> Icon(Icons.Filled.Person, contentDescription = route.label)
                                }
                            },
                            label = { Text(route.label) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        // continutul ecranelor gestionat de NavHost
        AppNavHost(
            navController = navController,
            padding = padding,
            prefs = prefs,
            darkTheme = darkTheme,
            onToggleDark = onToggleDark
        )
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    padding: PaddingValues,
    prefs: PreferencesManager,
    darkTheme: Boolean,
    onToggleDark: (Boolean) -> Unit
) {
    // host de navigatie care contine toate rutele
    NavHost(
        navController = navController,
        startDestination = Route.Login.path,
        modifier = Modifier.padding(padding)
    ) {
        // ecran login
        composable(Route.Login.path) {
            LoginScreen(
                prefs = prefs,
                onLoggedIn = {
                    // dupa login mergem la movies si scoatem login din backstack
                    navController.navigate(Route.Movies.path) {
                        popUpTo(Route.Login.path) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        // ecran lista filme
        composable(Route.Movies.path) {
            MoviesListScreen(
                onOpenSettings = { navController.navigate(Route.Settings.path) }
            )
        }
        // ecran setari
        composable(Route.Settings.path) {
            SettingsScreen(
                prefs = prefs,
                darkTheme = darkTheme,
                onToggleDark = onToggleDark,
                onBack = { navController.popBackStack() }
            )
        }
        // ecran vreme
        composable(Route.Weather.path) {
            WeatherScreen(
                latitude = 46.7667,
                longitude = 23.6
            )
        }
    }
}
