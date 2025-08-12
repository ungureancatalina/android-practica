package com.example.practica.presentation.bmi

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BmiScreenWhite() {
    val context = LocalContext.current

    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorBackground = MaterialTheme.colorScheme.background
    val colorOnPrimary = MaterialTheme.colorScheme.onPrimary

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(24.dp)
    ) {
        // buton de back
        IconButton(
            onClick = { (context as? Activity)?.finish() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = colorPrimary)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("BMI Calculator", color = colorPrimary, fontSize = 24.sp)

            // input inaltime
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

            // input greutate
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

            // buton calcul BMI
            Button(
                onClick = {
                    val h = height.toFloatOrNull()
                    val w = weight.toFloatOrNull()
                    result = if (h != null && w != null && h > 0) {
                        val bmi = w / ((h / 100) * (h / 100))
                        "Your BMI is %.2f".format(bmi)
                    } else {
                        "Enter valid values"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrimary,
                    contentColor = colorBackground
                )
            ) {
                Text("Calculate")
            }

            // afisare rezultat
            Text(text = result, color = colorPrimary, fontSize = 18.sp)
        }
    }
}
