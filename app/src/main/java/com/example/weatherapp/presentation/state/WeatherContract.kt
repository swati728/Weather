package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.model.WeatherInfo

/**
 * Single immutable state object the UI observes via StateFlow.
 * This is the "one state to rule the screen" half of UDF: it's always
 * current, replayable, and safe to re-collect after configuration changes.
 */
data class WeatherUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val weather: WeatherInfo? = null,
    val searchQuery: String = "",
    val errorMessage: String? = null,
    val isOffline: Boolean = false
)

/** All user actions flow into the ViewModel through one sealed entry point. */
sealed interface WeatherIntent {
    data class OnSearchQueryChanged(val query: String) : WeatherIntent
    data class OnSearchSubmitted(val city: String) : WeatherIntent
    object OnRefresh : WeatherIntent
    object OnRetryClicked : WeatherIntent
    data class OnUseCurrentLocation(val lat: Double, val lon: Double) : WeatherIntent
}

/**
 * One-off, non-replayable side effects (snackbar text, navigation, haptics).
 * These live on SharedFlow rather than StateFlow because they should fire
 * exactly once per emission, not be re-delivered to late collectors.
 */
sealed interface WeatherEvent {
    data class ShowSnackbar(val message: String) : WeatherEvent
    object ScrollToTop : WeatherEvent
}
