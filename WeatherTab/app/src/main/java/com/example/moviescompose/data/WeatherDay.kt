package com.example.moviescompose.data

// model de domeniu care reprezinta o zi de vreme
data class WeatherDay(
    val dateIso: String,
    val code: Int,
    val tMin: Double,
    val tMax: Double
)
