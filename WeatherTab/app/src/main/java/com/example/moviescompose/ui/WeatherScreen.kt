package com.example.moviescompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.Umbrella
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviescompose.R
import com.example.moviescompose.data.WeatherDatabase
import com.example.moviescompose.data.OpenMeteoApi
import com.example.moviescompose.data.WeatherRepositoryImpl
import com.example.moviescompose.data.WeatherDay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.platform.*
import androidx.compose.material.icons.outlined.*

private fun formatDateShort(dateIso: String): String = try {
    val inFmt = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val date: Date = inFmt.parse(dateIso)!!
    val out = SimpleDateFormat("EEE d MMM", Locale("ro"))
    out.format(date).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("ro")) else it.toString() }
} catch (_: Throwable) {
    dateIso
}

@Composable
private fun wmoToIcon(code: Int) = when (code) {
    0 -> Icons.Outlined.WbSunny                    // senin
    1, 2, 3 -> Icons.Outlined.Cloud                // partial noros
    45, 48 -> Icons.Outlined.Cloud                 // ceata
    51, 53, 55 -> Icons.Outlined.Grain             // burnita
    61, 63, 65 -> Icons.Outlined.WaterDrop         // ploaie
    71, 73, 75 -> Icons.Outlined.AcUnit            // ninsoare
    80, 81, 82 -> Icons.Outlined.Umbrella          // averse
    95, 96, 99 -> Icons.Outlined.Thunderstorm      // furtuna
    else -> Icons.Outlined.Cloud
}

// ecranul meteo - afiseaza vremea pe zile folosind datele din DB (actualizate din API)
@Composable
fun WeatherScreen(
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    // initializam baza de date Room
    val db = remember { WeatherDatabase.build(context.applicationContext) }
    // initializam API Retrofit
    val api = remember { OpenMeteoApi.create() }
    // repository care combina DB + API
    val repo = remember { WeatherRepositoryImpl(api, db.weatherDao()) }

    // ViewModel-ul pentru vreme
    val vm: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(repo, latitude, longitude)
    )


    // colectam starea curenta a UI-ului (loading, date, eroare)
    val state by vm.state.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // bara de sus cu titlu + buton refresh
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.weather_title),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.SemiBold
            )
            // butonul Refresh - actualizeaza DB-ul din API
            FilledTonalButton(onClick = { scope.launch { vm.refreshNow() } },colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary) ) {
                Icon(
                    Icons.Outlined.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.width(8.dp))
                Text("Refresh")
            }
        }

        Spacer(Modifier.height(14.dp))

        // afisam in functie de stare: loading, eroare, date
        when {
            // caz incarcare initiala fara date
            state.isLoading && state.items.isEmpty() -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            // caz eroare si nu avem nimic in DB
            state.error != null && state.items.isEmpty() -> {
                Text(
                    text = stringResource(R.string.error_format, state.error ?: ""),
                    color = MaterialTheme.colorScheme.error
                )
            }

            // avem date in DB
            else -> {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    // cardul mare "Currently" (ziua curenta)
                    if (state.items.isNotEmpty()) {
                        CurrentWeatherCard(state.items.first(), location = "Cluj-Napoca")
                    }

                    // lista pe zile
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(state.items) { day ->
                            WeatherRowCard(day)
                        }
                    }
                }
            }
        }
    }
}


// card pentru o zi din lista
@Composable
private fun WeatherRowCard(day: WeatherDay) {

    // formatam data
    val date = remember(day.dateIso) { formatDateShort(day.dateIso) }
    // luam icon in functie de cod WMO
    val icon = wmoToIcon(day.code)

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // icon meteo stanga
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 2.dp
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(8.dp)
                )
            }

            Spacer(Modifier.width(14.dp))

            // centru - text cu descriere vreme + data
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = wmoToText(day.code),  // "Senin", "Ploaie" etc.
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = date,   //  "Lun 20 Aug"
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }

            // dreapta - temperaturi min/max
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = String.format(Locale.ROOT, "%.0f°", day.tMax),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = String.format(Locale.ROOT, "%.0f°", day.tMin),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
        }
    }
}

// cardul mare "Currently" afisat sus
@Composable
private fun CurrentWeatherCard(day: WeatherDay, location: String = "Cluj-Napoca") {
    val icon = wmoToIcon(day.code)

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // partea stanga - texte + temperatura
            Column {
                Text(
                    text = "Currently",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = wmoToText(day.code),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = String.format("%.0f°", day.tMax),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // partea dreapta - icon mare + locatie
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(92.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = location,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
