package com.example.moviescompose.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.data.movieList

@Composable
fun MoviesListScreen(onOpenSettings: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Lista filme",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.weight(1f)

            )
            Button(onClick = onOpenSettings) { Text("Setări", color= MaterialTheme.colorScheme.onTertiary) }
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn(Modifier.fillMaxSize()) {
            items(movieList) { movie ->
                MovieCard(movie) {
                    Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}