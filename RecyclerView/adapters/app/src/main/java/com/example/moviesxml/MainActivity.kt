package com.example.moviesxml

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {

    // Lista statica de filme
    private val movieList = listOf(
        Movie("The Shawshank Redemption", "Drama", 1994, 9.3),
        Movie("The Godfather", "Crime", 1972, 9.2),
        Movie("The Dark Knight", "Action", 2008, 9.0),
        Movie("Pulp Fiction", "Crime", 1994, 8.9),
        Movie("Inception", "Sci-Fi", 2010, 8.8),
        Movie("Fight Club", "Drama", 1999, 8.8),
        Movie("Forrest Gump", "Drama", 1994, 8.8),
        Movie("The Matrix", "Sci-Fi", 1999, 8.7),
        Movie("Interstellar", "Sci-Fi", 2014, 8.6),
        Movie("The Green Mile", "Drama", 1999, 8.6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Layoutul ecranului care contine RecyclerView
        setContentView(R.layout.activity_main)

        // Referinta la RecyclerView din XML
        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)

        // LayoutManager (lista verticala)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // O linie despartitoare intre item-uri
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        // Adapterul si definerea a ce se intampla la click pe item
        val adapter = MovieAdapter(movieList) { movie ->
            // La click pe item → afisam un Toast cu titlul filmului
            Toast.makeText(this, "Ai ales: ${movie.title}", Toast.LENGTH_SHORT).show()
        }

        // Atasare adapter la RecyclerView
        recyclerView.adapter = adapter
    }
}