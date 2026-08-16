package com.example.weatherapp.data.local

import androidx.room.*
import com.example.weatherapp.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "weather_cache")
data class WeatherEntity(
    @PrimaryKey val cityKey: String, // normalized city name or "lat,lon"
    val cityName: String,
    val temperatureC: Double,
    val feelsLikeC: Double,
    val condition: String,
    val conditionIconUrl: String,
    val humidity: Int,
    val windKph: Double,
    val lastUpdatedEpochMillis: Long
)

fun WeatherEntity.toDomain(fromCache: Boolean = true) = WeatherInfo(
    cityName = cityName,
    temperatureC = temperatureC,
    feelsLikeC = feelsLikeC,
    condition = condition,
    conditionIconUrl = conditionIconUrl,
    humidity = humidity,
    windKph = windKph,
    lastUpdatedEpochMillis = lastUpdatedEpochMillis,
    isFromCache = fromCache
)

fun WeatherInfo.toEntity(cityKey: String) = WeatherEntity(
    cityKey = cityKey,
    cityName = cityName,
    temperatureC = temperatureC,
    feelsLikeC = feelsLikeC,
    condition = condition,
    conditionIconUrl = conditionIconUrl,
    humidity = humidity,
    windKph = windKph,
    lastUpdatedEpochMillis = lastUpdatedEpochMillis
)

@Dao
interface WeatherDao {
    // Flow so the UI/Repository can react to cache updates reactively (single source of truth).
    @Query("SELECT * FROM weather_cache WHERE cityKey = :cityKey LIMIT 1")
    fun observeByCity(cityKey: String): Flow<WeatherEntity?>

    @Query("SELECT * FROM weather_cache WHERE cityKey = :cityKey LIMIT 1")
    suspend fun getByCity(cityKey: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: WeatherEntity)

    @Query("DELETE FROM weather_cache WHERE cityKey = :cityKey")
    suspend fun delete(cityKey: String)
}

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
