package com.example.weatherapp.data.repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.local.toDomain
import com.example.weatherapp.data.local.toEntity
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.model.toDomain
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String, val cachedData: WeatherInfo? = null) : Resource<Nothing>()
}

@Singleton
class WeatherRepository @Inject constructor(
    private val api: WeatherApiService,
    private val dao: WeatherDao
) {
    /**
     * Network-first with cache fallback:
     * 1. Try the network so the user always sees fresh data when possible.
     * 2. On success, persist to Room (this becomes the resilient local cache).
     * 3. On any network failure, fall back to the last cached value instead
     *    of showing a dead end -- this is what keeps the app usable offline.
     */
    suspend fun fetchWeather(city: String): Resource<WeatherInfo> {
        val cityKey = city.trim().lowercase()
        return try {
            val dto = api.getCurrentWeather(apiKey = BuildConfig.WEATHER_API_KEY, query = city)
            val domain = dto.toDomain(fromCache = false)
            dao.upsert(domain.toEntity(cityKey))
            Resource.Success(domain)
        } catch (e: IOException) {
            // No connectivity / timeout -> serve cache if we have it.
            val cached = dao.getByCity(cityKey)?.toDomain(fromCache = true)
            if (cached != null) {
                Resource.Error("Offline - showing last saved data", cached)
            } else {
                Resource.Error("No internet connection and no cached data available")
            }
        } catch (e: Exception) {
            val cached = dao.getByCity(cityKey)?.toDomain(fromCache = true)
            Resource.Error(e.message ?: "Unknown error occurred", cached)
        }
    }

    /** Emits whatever is currently cached, immediately, for instant cold-start UI. */
    suspend fun getCachedWeather(city: String): WeatherInfo? {
        val cityKey = city.trim().lowercase()
        return dao.observeByCity(cityKey).first()?.toDomain(fromCache = true)
    }
}
