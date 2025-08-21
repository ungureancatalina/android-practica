package com.example.moviescompose.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

// interfata Retrofit pentru API-ul OpenMeteo
interface OpenMeteoApi {

    // request GET pentru forecast zilnic
    @GET("v1/forecast")
    suspend fun getDailyForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): ForecastResponse

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/"

        // metoda de creare a unei instante de API folosind Retrofit + Moshi
        fun create(): OpenMeteoApi {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(OpenMeteoApi::class.java)
        }
    }
}

// DTO-uri Moshi pentru raspunsul daily
data class ForecastResponse(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val generationtime_ms: Double? = null,
    val utc_offset_seconds: Int? = null,
    val timezone: String? = null,
    val timezone_abbreviation: String? = null,
    val elevation: Double? = null,
    val daily_units: DailyUnits,
    val daily: DailyData
)

data class DailyUnits(
    val time: String? = null,
    val weather_code: String? = null,
    val temperature_2m_max: String? = null,
    val temperature_2m_min: String? = null
)

data class DailyData(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)
