package com.example.moviesxml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesxml.data.*


class MainActivity : AppCompatActivity() {

    private lateinit var prefs: PreferencesManager
    private var appliedTheme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferencesManager(this)
        appliedTheme = prefs.getTheme()
        when (appliedTheme) {
            PreferencesManager.THEME_DARK -> setTheme(R.style.Theme_MyApp_Dark)
            else -> setTheme(R.style.Theme_MyApp_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val rv = findViewById<RecyclerView>(R.id.rvMovies)

        val movieList = listOf(
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

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MovieAdapter(movieList) { movie ->
            Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val current = prefs.getTheme()
        if (current != appliedTheme) {
            appliedTheme = current
            recreate()
        }
    }
}
