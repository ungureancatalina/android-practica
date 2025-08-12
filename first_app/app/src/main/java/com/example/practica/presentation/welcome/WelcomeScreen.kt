package com.example.practica.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    username: String,
    onNavigateToBmi: () -> Unit,
    onNavigateToParity: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorOnPrimary = MaterialTheme.colorScheme.onPrimary

    // Fundal cu gradient vertical (primary + secondary)
    val gradient = Brush.verticalGradient(
        colors = listOf(
            colorPrimary,
            MaterialTheme.colorScheme.secondary
        )
    )

    // Layout principal (Box pe toata suprafata)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(32.dp)
    ) {
        // Buton de back in coltul din stanga sus
        IconButton(
            onClick = onNavigateToLogin,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = colorOnPrimary
            )
        }

        // Continut centrat vertical: mesaj + butoane
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mesaj de intampinare
            Text(
                text = "Hello, $username!",
                fontSize = 26.sp,
                color = colorOnPrimary,
                style = MaterialTheme.typography.titleLarge
            )

            // Buton catre ecranul Parity
            Button(
                onClick = onNavigateToParity,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorPrimary
                )
            ) {
                Text("Check Parity")
            }

            // Buton catre ecranul BMI
            Button(
                onClick = onNavigateToBmi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorPrimary
                )
            ) {
                Text("Calculate BMI")
            }
        }
    }
}
