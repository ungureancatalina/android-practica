package com.example.moviescompose.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

// implementarea repository-ului ce leaga API-ul si baza de date
class WeatherRepositoryImpl(

    // sursa de date externa
    private val api: OpenMeteoApi,

    // sursa locala (Room)
    private val dao: WeatherDao
) : WeatherRepository {


    // observeDays intoarce un Flow din DB, mapat in modelul de domeniu
    override fun observeDays(): Flow<List<WeatherDay>> =
        dao.observeAllOrdered().map { list -> list.map { it.toDomain() } }

    // refresh face request la API si salveaza in DB
    override suspend fun refresh(lat: Double, lon: Double) = withContext(Dispatchers.IO) {
        val response = api.getDailyForecast(
            latitude = lat,
            longitude = lon,
            daily = "weather_code,temperature_2m_max,temperature_2m_min",
            timezone = "auto"
        )
        val entities = response.toEntities()

        // inlocuieste toate datele in DB
        dao.replaceAll(entities)
    }
}
