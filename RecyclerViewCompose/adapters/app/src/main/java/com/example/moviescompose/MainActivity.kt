package com.example.moviescompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import com.example.moviescompose.MovieListScreen
import com.example.moviescompose.MovieData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Surface = container cu un fundal pe toata suprafata
            Surface(color = MaterialTheme.colorScheme.background) {
                val context = LocalContext.current

                MovieListScreen(
                    // luam lista dintr-un obiect separat
                    movies = MovieData.movieList,
                    onItemClick = { movie ->
                        // Toast intr-un loc scurt, nu bagam logica UI aici
                        android.widget.Toast.makeText(
                            context,
                            "Ai ales: ${movie.title}",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}