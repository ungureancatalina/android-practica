package com.example.practica.presentation.welcome

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign

@Composable
fun WelcomeScreenWhite(username: String) {
    val context = LocalContext.current

    // variabile pentru input si rezultat
    var numberText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorBackground = MaterialTheme.colorScheme.background
    val colorOnPrimary = MaterialTheme.colorScheme.onPrimary

    // container principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(horizontal = 24.dp)
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
                tint = colorPrimary
            )
        }

        // continutul centrat in coloana
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // mesaj de bun venit
            Text(
                text = "Hello, $username!",
                fontSize = 26.sp,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                color = colorPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // input pentru numar
            OutlinedTextField(
                value = numberText,
                onValueChange = { numberText = it },
                label = { Text("Enter a number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorPrimary,
                    unfocusedBorderColor = colorPrimary,
                    cursorColor = colorPrimary,
                    focusedLabelColor = colorPrimary,
                    unfocusedLabelColor = colorPrimary,
                    focusedTextColor = colorPrimary,
                    unfocusedTextColor = colorPrimary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // buton care verifica paritate
            Button(
                onClick = {
                    val num = numberText.toIntOrNull()
                    result = when {
                        num == null -> "Please enter a valid number."
                        num % 2 == 0 -> "$num is even."
                        else -> "$num is odd."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrimary,
                    contentColor = colorBackground
                )
            ) {
                Text("Check", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // afisare rezultat
            Text(
                text = result,
                fontSize = 18.sp,
                color = colorPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}
