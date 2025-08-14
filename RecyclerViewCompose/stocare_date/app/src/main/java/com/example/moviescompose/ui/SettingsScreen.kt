package com.example.moviescompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.PreferencesManager

@Composable
fun SettingsScreen(
    prefs: PreferencesManager,
    darkTheme: Boolean,
    onToggleDark: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) { Text("←") }
            Spacer(Modifier.width(8.dp))
            Text("Setari tema", fontWeight = FontWeight.Bold, fontSize = 40.sp)
        }
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark theme", fontSize = 18.sp, modifier = Modifier.weight(1f))
            Switch(checked = darkTheme, onCheckedChange = { onToggleDark(it) })
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Salveaza") }
    }
}