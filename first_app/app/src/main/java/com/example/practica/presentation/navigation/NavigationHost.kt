package com.example.practica.presentation.navigation

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.practica.presentation.login.*
import com.example.practica.presentation.bmi.*
import com.example.practica.presentation.signup.*
import com.example.practica.presentation.welcome.*
import com.example.practica.presentation.parity.*

@Composable
fun NavigationHost(navController: NavHostController) {
    val context = LocalContext.current
    val startDestination = "login/dark"

    NavHost(navController = navController, startDestination = startDestination) {

        // Ecran de login cu tema dinamica
        composable("login/{theme}") { backStackEntry ->
            val theme = backStackEntry.arguments?.getString("theme") ?: "dark"
            val isWhite = theme == "white"

            if (isWhite) {
                LoginScreenWhite(
                    onLoginClick = { username, password ->
                        val isValid = username == "a" && password == "a"
                        if (isValid) {
                            navController.navigate("welcome/$username/white")
                        } else {
                            Toast.makeText(context, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onNavigateToSignUp2 = {
                        navController.navigate("signup/white")
                    }
                )
            } else {
                LoginScreen(
                    onLoginClick = { username, password ->
                        val isValid = username == "a" && password == "a"
                        if (isValid) {
                            navController.navigate("welcome/$username/dark")
                        } else {
                            Toast.makeText(context, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate("signup/dark")
                    }
                )
            }
        }

        // Ecran de inregistrare cu tema dinamica
        composable("signup/{theme}") { backStackEntry ->
            val theme = backStackEntry.arguments?.getString("theme") ?: "dark"
            val isWhite = theme == "white"

            if (isWhite) {
                SingUpScreenWhite(
                    onSignUpClick = { _, _ ->
                        Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    onNavigateBackToLogin2 = {
                        navController.popBackStack()
                    }
                )
            } else {
                SingUpScreen(
                    onSignUpClick = { _, _ ->
                        Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    onNavigateBackToLogin = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Ecran de welcome cu tema si navigare dinamica
        composable("welcome/{username}/{theme}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "User"
            val isWhiteTheme = backStackEntry.arguments?.getString("theme") == "white"

            if (isWhiteTheme) {
                WelcomeScreenWhite(
                    username = username,
                    onNavigateToBmi = {
                        navController.navigate("bmi/white")
                    },
                    onNavigateToParity = {
                        navController.navigate("parity/white")
                    },
                    onNavigateToLogin = {
                        navController.navigate("login_white") {
                            popUpTo("welcome/$username/white") { inclusive = true }
                        }
                    }
                )
            } else {
                WelcomeScreen(
                    username = username,
                    onNavigateToBmi = {
                        navController.navigate("bmi/dark")
                    },
                    onNavigateToParity = {
                        navController.navigate("parity/dark")
                    },
                    onNavigateToLogin = {
                        navController.navigate("login") {
                            popUpTo("welcome/$username/dark") { inclusive = true }
                        }
                    }
                )
            }
        }

        // Ecran BMI cu selectie in functie de tema
        composable("bmi/{theme}") { backStackEntry ->
            val isWhiteTheme = backStackEntry.arguments?.getString("theme") == "white"
            if (isWhiteTheme) {
                BmiScreenWhite()
            } else {
                BmiScreen()
            }
        }

        // Ecran Parity cu selectie in functie de tema
        composable("parity/{theme}") { backStackEntry ->
            val isWhiteTheme = backStackEntry.arguments?.getString("theme") == "white"
            if (isWhiteTheme) {
                ParityScreenWhite()
            } else {
                ParityScreen()
            }
        }
    }
}
