package com.example.moviescompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moviescompose.data.WeatherDay
import com.example.moviescompose.data.WeatherRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repo: WeatherRepository, // repository cu API + DB
    private val lat: Double,             // latitudine locatie
    private val lon: Double              // longitudine locatie
) : ViewModel() {


    // structura de stare pentru UI
    data class UiState(
        val isLoading: Boolean = false,
        val items: List<WeatherDay> = emptyList(),
        val error: String? = null
    )

    // fluxuri interne pentru loading si error
    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    // combinam fluxurile cu lista din repository pentru a produce starea completa
    val state: StateFlow<UiState> =
        combine(repo.observeDays(), _loading, _error) { list, loading, err ->
            UiState(isLoading = loading, items = list, error = err)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState())

    init {
        // la initializare facem refresh automat
        viewModelScope.launch {
            refreshNow()
            debugPrintDb()
        }
    }

    // functie care face refresh: cheama API si actualizeaza DB
    suspend fun refreshNow() {
        _loading.value = true
        _error.value = null
        try {
            repo.refresh(lat, lon)
        } catch (t: Throwable) {
            _error.value = t.message ?: "Eroare necunoscuta"
        } finally {
            _loading.value = false
        }
    }

    private fun debugPrintDb() {
        viewModelScope.launch {
            // luam datele din Flow o singura data
            val list = repo.observeDays().first()
            android.util.Log.d("DB_CHECK", "Date in DB: $list")
        }
    }
}

// factory pentru a crea ViewModel-ul cu parametri (repo, lat, lon)
@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val repo: WeatherRepository,
    private val lat: Double,
    private val lon: Double
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repo, lat, lon) as T
    }
}
