package com.example.practica.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.practica.R
import androidx.compose.ui.graphics.*


@Composable
fun SingUpScreen(onSignUpClick: (String, String) -> Unit, onNavigateBackToLogin: () -> Unit) {
    // state pentru email si parola
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // toggle parola vizibila
    var passwordVisible by remember { mutableStateOf(false) }

    // culori din tema globala
    val colorPrimary = MaterialTheme.colorScheme.primary
    val colorSecondary = MaterialTheme.colorScheme.secondary
    val colorOnPrimary = MaterialTheme.colorScheme.onPrimary

    val gradient = Brush.verticalGradient(
        colors = listOf(colorPrimary, colorSecondary)
    )

    // layout principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 24.dp)
    ) {
        // buton inapoi in coltul stanga sus
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = colorOnPrimary
            )
        }

        // coloana cu campuri si butoane, pozitionata pe centru
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // camp pentru username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorOnPrimary,
                    focusedBorderColor = colorOnPrimary,
                    focusedLabelColor = colorOnPrimary,
                    unfocusedLabelColor = colorOnPrimary,
                    cursorColor = colorOnPrimary,
                    focusedLeadingIconColor = colorOnPrimary,
                    unfocusedLeadingIconColor = colorOnPrimary,
                    focusedTextColor = colorOnPrimary,
                    unfocusedTextColor = colorOnPrimary
                )
            )

            // camp pentru parola
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                // iconita parola ascunsa
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = colorOnPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                // buton care arata/ascunde parola
                trailingIcon = {
                    val visibilityIcon = if (passwordVisible)
                        painterResource(id = R.drawable.ic_visibility_off)
                    else
                        painterResource(id = R.drawable.ic_visibility)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = visibilityIcon,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = colorOnPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                // transforma textul in **** daca parolaVisible e false
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorOnPrimary,
                    focusedBorderColor = colorOnPrimary,
                    focusedLabelColor = colorOnPrimary,
                    unfocusedLabelColor = colorOnPrimary,
                    cursorColor = colorOnPrimary,
                    focusedLeadingIconColor = colorOnPrimary,
                    unfocusedLeadingIconColor = colorOnPrimary,
                    focusedTextColor = colorOnPrimary,
                    unfocusedTextColor = colorOnPrimary
                )
            )

            // buton sign up
            Button(
                onClick = { onSignUpClick(username, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorOnPrimary,
                    contentColor = colorPrimary
                )
            ) {
                Text("SIGNUP", fontWeight = FontWeight.Bold)
            }

            // link inapoi la login
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateBackToLogin() }
            ) {
                Text("Already have an account", color = colorOnPrimary)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Login", color = colorOnPrimary, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // separator cu OR
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = colorOnPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
                Text(
                    text = "  OR  ",
                    color = colorOnPrimary,
                    fontSize = 14.sp
                )
                Divider(
                    color = colorOnPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
            }

            Text(
                text = "Sign up with Social Networks",
                color = colorOnPrimary,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )

            // iconite pentru login social
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            width = 2.dp,
                            color = colorOnPrimary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorOnPrimary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            width = 2.dp,
                            color = colorOnPrimary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorOnPrimary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            width = 2.dp,
                            color = colorOnPrimary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorOnPrimary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = "Twitter",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
