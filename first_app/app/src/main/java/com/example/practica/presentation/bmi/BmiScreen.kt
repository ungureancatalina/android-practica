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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BmiScreen() {
    val context = LocalContext.current

    // culori din tema
    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorOnPrimary = MaterialTheme.colorScheme.onPrimary

    val gradient = Brush.verticalGradient(
        colors = listOf(colorPrimary, MaterialTheme.colorScheme.secondary)
    )

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp)
    ) {
        // buton de back
        IconButton(
            onClick = { (context as? Activity)?.finish() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = colorOnPrimary)
        }

        // conținutul ecranului
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("BMI Calculator", color = colorOnPrimary, fontSize = 24.sp)

            // input inaltime
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorOnPrimary,
                    unfocusedBorderColor = colorOnPrimary,
                    cursorColor = colorOnPrimary,
                    focusedLabelColor = colorOnPrimary,
                    unfocusedLabelColor = colorOnPrimary,
                    focusedTextColor = colorOnPrimary,
                    unfocusedTextColor = colorOnPrimary
                )
            )

            // input greutate
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorOnPrimary,
                    unfocusedBorderColor = colorOnPrimary,
                    cursorColor = colorOnPrimary,
                    focusedLabelColor = colorOnPrimary,
                    unfocusedLabelColor = colorOnPrimary,
                    focusedTextColor = colorOnPrimary,
                    unfocusedTextColor = colorOnPrimary
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
                    containerColor = Color.White,
                    contentColor = colorPrimary
                )
            ) {
                Text("Calculate")
            }

            // afisare rezultat
            Text(text = result, color = colorOnPrimary, fontSize = 18.sp)
        }
    }
}
