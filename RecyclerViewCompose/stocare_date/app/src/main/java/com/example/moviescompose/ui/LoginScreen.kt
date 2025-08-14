package com.example.moviescompose.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.PreferencesManager

@Composable
fun LoginScreen(
    prefs: PreferencesManager,
    onLoggedIn: () -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf(prefs.getUsername() ?: "") }
    var password by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login into your account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(Modifier.height(16.dp))

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val onPrimary = MaterialTheme.colorScheme.onBackground

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("User") },
                        singleLine = true,
                        modifier = Modifier.width(280.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = onPrimary,
                            unfocusedTextColor = onPrimary,
                            focusedLabelColor = onPrimary,
                            unfocusedLabelColor = onPrimary.copy(alpha = 0.8f),
                            cursorColor = onPrimary,
                            focusedBorderColor = onPrimary,
                            unfocusedBorderColor = onPrimary.copy(alpha = 0.6f),
                            focusedPlaceholderColor = onPrimary.copy(alpha = 0.6f),
                            unfocusedPlaceholderColor = onPrimary.copy(alpha = 0.6f)
                        )

                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Parola") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.width(280.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = onPrimary,
                            unfocusedTextColor = onPrimary,
                            focusedLabelColor = onPrimary,
                            unfocusedLabelColor = onPrimary.copy(alpha = 0.8f),
                            cursorColor = onPrimary,
                            focusedBorderColor = onPrimary,
                            unfocusedBorderColor = onPrimary.copy(alpha = 0.6f),
                            focusedPlaceholderColor = onPrimary.copy(alpha = 0.6f),
                            unfocusedPlaceholderColor = onPrimary.copy(alpha = 0.6f)
                        )

                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (username.isBlank() || password.isBlank()) {
                                Toast.makeText(context, "Completeaza user si parola", Toast.LENGTH_SHORT).show()
                            } else {
                                prefs.saveUsername(username.trim())
                                onLoggedIn()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Login", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}