package com.example.weatherapp.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState // CHANGED
import androidx.compose.animation.core.tween // CHANGED
import androidx.compose.foundation.background // CHANGED
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush // CHANGED
import androidx.compose.ui.graphics.Color // CHANGED
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.state.WeatherEvent
import com.example.weatherapp.presentation.state.WeatherIntent
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collectLatest
// ...same imports as before, minus androidx.compose.foundation.background and Brush (no longer used directly here)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    fun requestDeviceLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) return
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                viewModel.onIntent(WeatherIntent.OnUseCurrentLocation(location.latitude, location.longitude))
            }
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted -> if (granted) requestDeviceLocation() }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is WeatherEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
                WeatherEvent.ScrollToTop -> Unit
            }
        }
    }

    val condition = state.weather?.condition ?: "Clear"
    val gradient = getWeatherGradient(condition) // CHANGED: now returns a WeatherGradient with an `effect`

    Box(modifier = Modifier.fillMaxSize()) {
        WeatherBackground(gradient = gradient, modifier = Modifier.fillMaxSize()) // CHANGED

        Scaffold(
            containerColor = Color.Transparent,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("Weather") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                ) {
                    OutlinedTextField(
                        value = state.searchQuery,
                        onValueChange = { viewModel.onIntent(WeatherIntent.OnSearchQueryChanged(it)) },
                        label = { Text("Search city") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(onClick = {
                        val granted = ContextCompat.checkSelfPermission(
                            context, Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                        if (granted) requestDeviceLocation()
                        else locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }) {
                        Icon(Icons.Filled.MyLocation, contentDescription = "Use current location")
                    }
                }

                Spacer(Modifier.height(4.dp)) // CHANGED: was 16.dp — tighter gap, pulls content up under the search bar

                if (state.isOffline) {
                    AssistChip(
                        onClick = {},
                        label = { Text("Offline - showing cached data") },
                        leadingIcon = { Icon(Icons.Filled.CloudOff, contentDescription = null) }
                    )
                    Spacer(Modifier.height(12.dp))
                }

                when {
                    state.isLoading -> CircularProgressIndicator()

                    state.weather != null -> {
                        val w = state.weather!!
                        Column(
                            horizontalAlignment = Alignment.Start, // CHANGED: left-align the weather text block
                            modifier = Modifier.fillMaxWidth()      // CHANGED: needed so Start alignment has room to apply
                        ) {
                            Text(w.cityName, style = MaterialTheme.typography.headlineMedium)
                            Text("${w.temperatureC}°C", style = MaterialTheme.typography.displayLarge)
                            Text(w.condition, style = MaterialTheme.typography.bodyLarge)
                            Text("Feels like ${w.feelsLikeC}°C")
                            Text("Humidity ${w.humidity}% · Wind ${w.windKph} kph")
                        }
                    }

                    state.errorMessage != null -> {
                        Text(state.errorMessage ?: "", color = MaterialTheme.colorScheme.error)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.onIntent(WeatherIntent.OnRetryClicked) }) {
                            Icon(Icons.Filled.Refresh, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}