package com.example.moviescompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.data.Movie

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {

    // card pentru un film din lista
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            // actiune cand apesi pe card
            .clickable { onClick() }
            .padding(horizontal = 2.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // randul de sus cu titlu + rating
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // titlu film
                Text(
                    text = movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                // chip mic cu steaua si rating-ul filmului
                AssistChip(
                    onClick = { },
                    label = { Text(text = "★ ${movie.rating}") }
                )
            }

            Spacer(Modifier.height(6.dp))

            // rand cu genul si anul
            Row {
                Text(text = movie.genre, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.width(8.dp)); Text("•"); Spacer(Modifier.width(8.dp))
                Text(text = movie.year.toString(), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
