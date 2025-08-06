package com.example.practica.presentation.welcome

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.foundation.text.*
import androidx.compose.ui.text.input.*

@Composable
fun WelcomeScreen(username: String) {
    val context = LocalContext.current
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    // variabile pentru input si rezultat
    var numberText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // container principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        // buton de back (inchide activitatea curenta)
        IconButton(
            onClick = { (context as? Activity)?.finish() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        // continutul ecranului centrat
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // text de bun venit
            Text(
                text = "Hello, $username!",
                fontSize = 26.sp,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // input pentru numar
            TextField(
                value = numberText,
                onValueChange = { numberText = it },
                label = { Text("Enter a number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // buton care verifica paritate
            Button(onClick = {
                val num = numberText.toIntOrNull()
                result = when {
                    num == null -> "Please enter a valid number."
                    num % 2 == 0 -> "$num is even."
                    else -> "$num is odd."
                }
            }) {
                Text("Check")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // afisare rezultat
            Text(
                text = result,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
