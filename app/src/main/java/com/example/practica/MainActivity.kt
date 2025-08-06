package com.example.practica

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

//activitatea principala (punctul de start al aplicatiei)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //aplica tema globala
            MaterialTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //incarca logica de navigare intre ecrane
                    NavigationHost(navController)
                }
            }
        }
    }
}

//controleaza rutele aplicatiei
@Composable
fun NavigationHost(navController: NavHostController) {
    val context = LocalContext.current

    //definirea tuturor rutelor posibile si ecranelor asociate
    NavHost(navController = navController, startDestination = "login") {
        composable("login_white") {
            LoginScreenWhite(
                //verificare/hardcodat :))))
                onLoginClick = { username, password ->
                    val isValid = username == "admin" && password == "1234"
                    val msg = if (isValid) "Login successful!" else "Wrong credentials!"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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
                //revine la ecranul anterior
                onNavigateBackToLogin2  = {
                    navController.popBackStack()
                }
            )
        }
        composable("login") {
            LoginScreen(
                //verificare/hardcodat :))))
                onLoginClick = { username, password ->
                    val isValid = username == "admin" && password == "1234"
                    val msg = if (isValid) "Login successful!" else "Wrong credentials!"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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
                //revine la ecranul anterior
                onNavigateBackToLogin  = {
                    navController.popBackStack()
                }
            )
        }
    }
}
