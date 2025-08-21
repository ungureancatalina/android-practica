package com.example.moviescompose.ui

// definim rutele (ecranele) aplicatiei
// sealed class pentru a avea rute tip-safe
sealed class Route(val path: String, val label: String) {
    object Movies : Route("movies", "Filme")
    object Login : Route("login", "Login")
    object Settings : Route("settings", "Setari")
    object Weather : Route("weather", "Vreme")
}
