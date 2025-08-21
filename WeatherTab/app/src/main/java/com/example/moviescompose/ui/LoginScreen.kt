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
import com.example.moviescompose.data.PreferencesManager


@Composable
fun LoginScreen(
    prefs: PreferencesManager,
    onLoggedIn: () -> Unit
) {
    val context = LocalContext.current

    // citim user-ul salvat sau string gol
    var username by remember { mutableStateOf(prefs.getUsername() ?: "") }
    var password by remember { mutableStateOf("") }


    // fundalul ecranului
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
            // titlu
            Text(
                text = "Login into your account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(Modifier.height(16.dp))

            // card cu formularul
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

                    // camp pentru user
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

                    // camp pentru parola
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

                    // buton login
                    Button(
                        onClick = {
                            // daca lipseste ceva afisam mesaj
                            if (username.isBlank() || password.isBlank()) {
                                Toast.makeText(context, "Completeaza user si parola", Toast.LENGTH_SHORT).show()
                            } else {
                                // salvam user-ul si mergem mai departe
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
