package com.example.moviescompose.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = visiniu,
    onPrimary = light_accent,
    secondary = visiniu,
    onSecondary = text_on_visiniu_pink,
    background = pink_light_bg,
    surface = visiniu,
    onSurface = text_on_visiniu_pink,
    tertiary = light_accent,
    onTertiary = light_highlight
)

private val DarkColors = darkColorScheme(
    primary = pink_light,
    onPrimary = dark_accent,
    secondary = pink_light,
    onSecondary = text_on_pink_visiniu,
    background = dark_bg_visiniu,
    surface = pink_light,
    onSurface = text_on_pink_visiniu,
    tertiary = dark_accent,
    onTertiary = dark_highlight
)

@Composable
fun MoviesTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}