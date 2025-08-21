package com.example.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviescompose.ui.MoviesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // pornim aplicatia cu MoviesApp (care contine tema + navigatia)
        setContent { MoviesApp() }
    }
}
