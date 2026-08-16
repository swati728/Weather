package com.example.weatherapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clean domain model consumed by the UI layer.
 * Kept separate from the network DTO and the Room entity so that
 * changes to the API or the DB schema never leak into the UI.
 */
data class WeatherInfo(
    val cityName: String,
    val temperatureC: Double,
    val feelsLikeC: Double,
    val condition: String,
    val conditionIconUrl: String,
    val humidity: Int,
    val windKph: Double,
    val lastUpdatedEpochMillis: Long,
    val isFromCache: Boolean = false
)

// ---------- Network DTOs (shape of the raw API response) ----------

@Serializable
data class WeatherResponseDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val current: CurrentDto
)

@Serializable
data class LocationDto(
    @SerialName("name") val name: String
)

@Serializable
data class CurrentDto(
    @SerialName("temp_c") val tempC: Double,
    @SerialName("feelslike_c") val feelsLikeC: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("wind_kph") val windKph: Double,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Long
)

@Serializable
data class ConditionDto(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String
)

fun WeatherResponseDto.toDomain(fromCache: Boolean = false) = WeatherInfo(
    cityName = location.name,
    temperatureC = current.tempC,
    feelsLikeC = current.feelsLikeC,
    condition = current.condition.text,
    conditionIconUrl = "https:${current.condition.icon}",
    humidity = current.humidity,
    windKph = current.windKph,
    lastUpdatedEpochMillis = current.lastUpdatedEpoch * 1000,
    isFromCache = fromCache
)
