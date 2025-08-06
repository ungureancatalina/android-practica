package com.example.practica.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.practica.ui.theme.PracticaTheme
import com.example.practica.presentation.welcome.WelcomeScreen
import com.example.practica.presentation.welcome.WelcomeScreenWhite

class WelcomeActivity : ComponentActivity() {

    // se apeleaza prima data cand activitatea este creata
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // extrage username-ul si theme-ul transmis prin intent
        val username = intent.getStringExtra("username") ?: "User"
        val isWhiteTheme = intent.getBooleanExtra("isWhiteTheme", false)

        setContent {
            PracticaTheme {
                if (isWhiteTheme) {
                    WelcomeScreenWhite(username)
                } else {
                    WelcomeScreen(username)
                }
            }
        }
    }

    // se apeleaza cand activitatea devine vizibila
    override fun onStart() {
        super.onStart()
    }

    // se apeleaza cand activitatea devine interactiva
    override fun onResume() {
        super.onResume()
    }

    // se apeleaza cand activitatea pierde focusul (dar este inca vizibila)
    override fun onPause() {
        super.onPause()
    }

    // se apeleaza cand activitatea nu mai este vizibila
    override fun onStop() {
        super.onStop()
    }

    // se apeleaza inainte ca activitatea sa fie distrusa
    override fun onDestroy() {
        super.onDestroy()
    }
}
