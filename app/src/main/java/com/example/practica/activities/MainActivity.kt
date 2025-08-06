package com.example.practica.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.practica.presentation.login.LoginScreen
import com.example.practica.presentation.login.LoginScreenWhite
import com.example.practica.presentation.signup.SingUpScreen
import com.example.practica.presentation.signup.SingUpScreenWhite
import com.example.practica.ui.theme.PracticaTheme
import com.example.practica.activities.WelcomeActivity

//activitatea principala (punctul de start al aplicatiei)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // aplica tema globala
            PracticaTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // incarca logica de navigare intre ecrane
                    NavigationHost(navController)
                }
            }
        }
    }
}

// controleaza rutele aplicatiei
@Composable
fun NavigationHost(navController: NavHostController) {
    val context = LocalContext.current
    var theme_current= "login_white"

    // definirea tuturor rutelor posibile si ecranelor asociate
    NavHost(navController = navController, startDestination = theme_current) {
        composable("login_white") {
            LoginScreenWhite(
                // verificare/hardcodat :))))
                onLoginClick = { username, password ->
                    val isValid = username == "a" && password == "a"
                    if (isValid) {
                        val intent = Intent(context, WelcomeActivity::class.java).apply {
                            putExtra("username", username)
                            putExtra("isWhiteTheme", true)
                        }
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                    }
                },
                onNavigateToSignUp2 = {
                    navController.navigate("signup_white")
                }
            )
        }
        composable("signup_white") {
            SingUpScreenWhite(
                onSignUpClick = { username, password ->
                    Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                // revine la ecranul anterior
                onNavigateBackToLogin2 = {
                    navController.popBackStack()
                }
            )
        }
        composable("login") {
            LoginScreen(
                // verificare/hardcodat :))))
                onLoginClick = { username, password ->
                    val isValid = username == "a" && password == "a"
                    if (isValid) {
                        val intent = Intent(context, WelcomeActivity::class.java).apply {
                            putExtra("username", username)
                            putExtra("isWhiteTheme", false)
                        }
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SingUpScreen(
                onSignUpClick = { username, password ->
                    Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                // revine la ecranul anterior
                onNavigateBackToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
