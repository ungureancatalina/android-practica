package com.example.moviescompose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Typography


// definim culorile pentru light si dark theme
private val LightColors = lightColorScheme(
    primary = LightPrimary, onPrimary = LightOnPrimary,
    secondary = LightSecondary, onSecondary = LightOnSecondary,
    background = LightBackground, onBackground = LightOnBackground,
    surface = LightSurface, onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant, onSurfaceVariant = LightOnSurfaceVariant,
    tertiary = LightTertiary, onTertiary = LightOnTertiary,
    error = LightError, onError = LightOnError
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimary, onPrimary = DarkOnPrimary,
    secondary = DarkSecondary, onSecondary = DarkOnSecondary,
    background = DarkBackground, onBackground = DarkOnBackground,
    surface = DarkSurface, onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant, onSurfaceVariant = DarkOnSurfaceVariant,
    tertiary = DarkTertiary, onTertiary = DarkOnTertiary,
    error = DarkError, onError = DarkOnError
)

// formele (rotunjiri) folosite in toata aplicatia
private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small      = RoundedCornerShape(12.dp),
    medium     = RoundedCornerShape(16.dp),
    large      = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

// composable global care aplica tema aplicatiei
@Composable
fun MoviesTheme(
    // daca folosim tema dark
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        shapes = AppShapes,
        content = content
    )
}
