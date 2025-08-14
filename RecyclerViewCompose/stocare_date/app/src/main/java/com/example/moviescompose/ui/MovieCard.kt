package com.example.moviescompose.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.data.Movie

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondary)
            Spacer(Modifier.height(6.dp))
            Row {
                Text(text = movie.genre, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.width(8.dp))
                Text("•")
                Spacer(Modifier.width(8.dp))
                Text(text = movie.year.toString(), style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "★ ${movie.rating}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )        }
    }
}