package com.example.moviescompose.data

// extensie care transforma un WeatherDayEntity din DB in modelul de domeniu WeatherDay
fun WeatherDayEntity.toDomain() = WeatherDay(dateIso, weatherCode, tempMinC, tempMaxC)

// extensie care transforma raspunsul API in lista de entitati pentru DB
fun ForecastResponse.toEntities(): List<WeatherDayEntity> {
    val times = daily.time
    val codes = daily.weather_code
    val tmax = daily.temperature_2m_max
    val tmin = daily.temperature_2m_min

    // luam dimensiunea minima ca sa evitam erori daca listele difera
    val n = listOf(times.size, codes.size, tmax.size, tmin.size).minOrNull() ?: 0

    // cream entitatile pentru DB
    return (0 until n).map { i ->
        WeatherDayEntity(
            dateIso = times[i],
            weatherCode = codes[i],
            tempMaxC = tmax[i],
            tempMinC = tmin[i]
        )
    }
}
