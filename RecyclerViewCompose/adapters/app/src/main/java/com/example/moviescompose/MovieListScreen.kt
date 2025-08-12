package com.example.moviescompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.style.*


private val BgXml        = Color(0xFFE7CECE)
private val CardOuter    = Color(0xFFBD9494)
private val CardInner    = Color(0xFF591B1B)
private val TitleColor   = Color(0xFFF0E7E7)
private val SubtleText   = Color(0xFFDCCBCB)
private val RatingColor  = Color(0xFFFFE6A7)

// Ecranul principal cu lista de filme
@Composable
fun MovieListScreen(
    movies: List<Movie>,
    onItemClick: (Movie) -> Unit
) {
    // LazyColumn lista optimizata (echivalent RecyclerView din XML)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgXml)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(movies, key = { it.title }) { movie ->
                MovieRow(
                    movie = movie,
                    onClick = { onItemClick(movie) }
                )
            }
        }
    }
}

// Un singur rand din lista
@Composable
fun MovieRow(
    movie: Movie,
    onClick: () -> Unit
) {
    // Card
    Card(
        onClick = onClick,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardOuter
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardInner)
                .padding(14.dp)
        ) {
            // Titlul filmului
            Text(
                text = movie.title,
                color = TitleColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.02.em,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 24.sp
            )

            Spacer(Modifier.height(6.dp))

            // Gen + an
            Text(
                text = "${movie.genre} • ${movie.year}",
                color = SubtleText.copy(alpha = 0.9f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            // Rating cu stea galbena
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "⭐ ${"%.1f".format(movie.rating)}",
                    color = RatingColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}