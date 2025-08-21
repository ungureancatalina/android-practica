package com.example.moviescompose.data

// model de domeniu pentru filme
data class Movie(
    val title: String,
    val genre: String,
    val year: Int,
    val rating: Double
)