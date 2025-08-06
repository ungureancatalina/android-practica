package com.example.practica

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*


@Composable
fun LoginScreenWhite(onLoginClick: (String, String) -> Unit, onNavigateToSignUp2: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val primaryColor = Color(0xFF10687C)
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF10687C), Color(0xFF0B4D42))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        IconButton(
            onClick = { /* TODO */  },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = primaryColor
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = primaryColor,
                    focusedBorderColor = primaryColor,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = primaryColor,
                    cursorColor = primaryColor,
                    focusedLeadingIconColor = primaryColor,
                    unfocusedLeadingIconColor = primaryColor,
                    focusedTextColor = primaryColor,
                    unfocusedTextColor = primaryColor
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null,tint = primaryColor,modifier = Modifier.size(20.dp) ) },
                trailingIcon = {
                    val visibilityIcon = if (passwordVisible)
                        painterResource(id = R.drawable.ic_visibility_off)
                    else
                        painterResource(id = R.drawable.ic_visibility)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = visibilityIcon,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = primaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth() ,
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = primaryColor,
                    focusedBorderColor = primaryColor,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = primaryColor,
                    cursorColor = primaryColor,
                    focusedLeadingIconColor = primaryColor,
                    unfocusedLeadingIconColor = primaryColor,
                    focusedTextColor = primaryColor,
                    unfocusedTextColor = primaryColor
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(50))
                    .background(gradient)
                    .clickable { onLoginClick(username, password) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().clickable { onNavigateToSignUp2()}
            ) {
                Text("Don't have an account?", color = primaryColor)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Sign Up", color = primaryColor, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                color = primaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = primaryColor,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
                Text(
                    text = "  OR  ",
                    color = primaryColor,
                    fontSize = 14.sp
                )
                Divider(
                    color = primaryColor,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
            }

            Text(
                text = "Login with Social Networks",
                color = primaryColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                IconButton(onClick = {  }, modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 2.dp,
                        color = primaryColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent))  {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = {  }, modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 2.dp,
                        color = primaryColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent))  {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = { }, modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 2.dp,
                        color = primaryColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent)) {
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
