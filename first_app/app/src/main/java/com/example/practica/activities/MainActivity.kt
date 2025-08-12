package com.example.practica.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.practica.presentation.navigation.NavigationHost
import com.example.practica.ui.theme.PracticaTheme

class MainActivity : ComponentActivity() {

    private val TAG = "Lifecycle"

    // metoda apelata cand activitatea este creata
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate - initializare activitate si UI")

        setContent {
            PracticaTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost(navController)
                }
            }
        }
    }

    // activitatea devine vizibila
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart - activitatea este vizibila")
    }

    // activitatea este acum in prim-plan si interactiva
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume - activitatea este in prim-plan")
    }

    // apelata cand o alta activitate intra partial peste aceasta
    override fun onPause() {
        Log.d(TAG, "onPause - activitatea este partial ascunsa")
        super.onPause()
    }

    // activitatea nu mai este vizibila
    override fun onStop() {
        Log.d(TAG, "onStop - activitatea nu mai este vizibila")
        super.onStop()
    }

    // apelata cand activitatea revine dupa ce a fost oprita
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart - activitatea revine dupa onStop")
    }

    // apelata inainte ca activitatea sa fie distrusa
    override fun onDestroy() {
        Log.d(TAG, "onDestroy - activitatea este distrusa")
        super.onDestroy()
    }
}
