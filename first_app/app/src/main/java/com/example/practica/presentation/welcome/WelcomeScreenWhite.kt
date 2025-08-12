package com.example.practica.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun WelcomeScreenWhite(
    username: String,
    onNavigateToBmi: () -> Unit,
    onNavigateToParity: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorBackground = MaterialTheme.colorScheme.background

    // Container principal pe tot ecranul
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(horizontal = 24.dp)
    ) {
        // Buton de inapoi in coltul stanga sus
        IconButton(
            onClick = onNavigateToLogin, // navigheaza inapoi la ecranul de login
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = colorPrimary
            )
        }

        // Continut centrat: mesaj + butoane
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mesaj de intampinare
            Text(
                text = "Hello, $username!",
                fontSize = 26.sp,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                color = colorPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Buton catre ecranul de verificare paritate
            Button(
                onClick = onNavigateToParity,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrimary,
                    contentColor = colorBackground
                )
            ) {
                Text("Verifica Paritate", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buton catre ecranul de calcul BMI
            Button(
                onClick = onNavigateToBmi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrimary,
                    contentColor = colorBackground
                )
            ) {
                Text("Calculeaza BMI", fontSize = 16.sp)
            }
        }
    }
}
