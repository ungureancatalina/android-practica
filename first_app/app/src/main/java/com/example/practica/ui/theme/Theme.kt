package com.example.practica.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryDark,
    background = Color.White,
    surface = Color.White,
    onPrimary = TextOnPrimary,
    onSecondary = TextOnPrimary,
    onBackground = PrimaryColor,
    onSurface = PrimaryColor
)

@Composable
fun PracticaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
