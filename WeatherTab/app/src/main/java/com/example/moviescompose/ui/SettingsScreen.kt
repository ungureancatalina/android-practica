package com.example.moviescompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescompose.data.PreferencesManager

@Composable
fun SettingsScreen(
    prefs: PreferencesManager,       // manager pentru salvarea preferintelor
    darkTheme: Boolean,              // daca este tema intunecata/luminata
    onToggleDark: (Boolean) -> Unit, // callback pentru schimbarea temei
    onBack: () -> Unit               // callback pentru butonul "Salveaza"
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
            "Setari tema",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // rand cu switch pentru tema
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Dark theme", fontSize = 18.sp, modifier = Modifier.weight(1f))
            Switch(checked = darkTheme, onCheckedChange = { onToggleDark(it) })
        }

        Spacer(Modifier.height(32.dp))

        // butonul pentru salvare (revine inapoi)
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Salveaza")
        }
    }
}
