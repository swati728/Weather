package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.Resource
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.state.WeatherEvent
import com.example.weatherapp.presentation.state.WeatherIntent
import com.example.weatherapp.presentation.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    // Private mutable state, public read-only StateFlow -> classic UDF encapsulation.
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    // replay = 0 so events fire once and are never redelivered on re-subscription
    // (e.g. after a config change), which is exactly what one-off effects need.
    private val _events = MutableSharedFlow<WeatherEvent>(replay = 0, extraBufferCapacity = 1)
    val events: SharedFlow<WeatherEvent> = _events.asSharedFlow()

    private var searchQueryFlow = MutableStateFlow("")
    private var fetchJob: Job? = null

    init {
        // Debounced search-as-you-type without spamming the network/DB.
        searchQueryFlow
            .debounce(400)
            .distinctUntilChanged()
            .onEach { query -> if (query.isNotBlank()) loadWeather(query) }
            .launchIn(viewModelScope)

        loadWeather("London") // sensible default on cold start
    }

    /** Single entry point for every user action -- the "intent" side of UDF. */
    fun onIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.OnSearchQueryChanged -> {
                _uiState.update { it.copy(searchQuery = intent.query) }
                searchQueryFlow.value = intent.query
            }
            is WeatherIntent.OnSearchSubmitted -> loadWeather(intent.city)
            is WeatherIntent.OnRetryClicked -> loadWeather(_uiState.value.searchQuery.ifBlank { "London" })
            is WeatherIntent.OnRefresh -> loadWeather(
                city = _uiState.value.weather?.cityName ?: "London",
                isPullToRefresh = true
            )
            is WeatherIntent.OnUseCurrentLocation ->
                loadWeather("${intent.lat},${intent.lon}")
        }
    }

    private fun loadWeather(city: String, isPullToRefresh: Boolean = false) {
        fetchJob?.cancel() // avoid stale responses racing a newer request
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = !isPullToRefresh,
                    isRefreshing = isPullToRefresh,
                    errorMessage = null
                )
            }

            when (val result = repository.fetchWeather(city)) {
                is Resource.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        weather = result.data,
                        isOffline = false,
                        errorMessage = null
                    )
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            weather = result.cachedData ?: it.weather,
                            isOffline = result.cachedData != null,
                            errorMessage = if (result.cachedData == null) result.message else null
                        )
                    }
                    // Errors are transient notifications -> SharedFlow, not state.
                    _events.emit(WeatherEvent.ShowSnackbar(result.message))
                }
            }
        }
    }
}
