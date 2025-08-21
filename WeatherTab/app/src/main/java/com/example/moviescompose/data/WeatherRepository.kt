package com.example.moviescompose.data

import kotlinx.coroutines.flow.Flow


// interfata pentru repository-ul de vreme, il respecta implementarea (WeatherRepositoryImpl)
interface WeatherRepository {

    // observa lista de zile (din baza de date Room) ca Flow
    fun observeDays(): Flow<List<WeatherDay>>

    // face refresh: ia datele noi din API si le salveaza in DB
    suspend fun refresh(lat: Double, lon: Double)
}
