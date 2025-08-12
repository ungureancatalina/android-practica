package com.example.practica.presentation.parity

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.*

@Composable
fun ParityScreenWhite() {
    val context = LocalContext.current
    var numberText by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    val colorPrimary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background

    //layout principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp)
    ) {
        //buton de inapoi la welcome screen
        IconButton(
            onClick = { (context as? Activity)?.finish() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = colorPrimary)
        }

        // coloana cu inputuri si butoane, pozitionata pe centru
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Check Parity", color = colorPrimary, fontSize = 24.sp)

            //inputul nr
            OutlinedTextField(
                value = numberText,
                onValueChange = { numberText = it },
                label = { Text("Enter a number") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            //buton de verificare
            Button(onClick = {
                val num = numberText.toIntOrNull()
                result = when {
                    num == null -> "Please enter a valid number."
                    num % 2 == 0 -> "$num is even"
                    else -> "$num is odd"
                }
            }) {
                Text("Check")
            }

            //outputul rezultat
            Text(result, color = colorPrimary, fontSize = 18.sp)
        }
    }
}
