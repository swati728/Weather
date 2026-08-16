package com.example.weatherapp.data.remote

import com.example.weatherapp.domain.model.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    // Swap base URL / params for whichever provider you use (WeatherAPI.com shape shown here).
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") query: String, // "London" or "lat,lon"
        @Query("aqi") aqi: String = "no"
    ): WeatherResponseDto
}
