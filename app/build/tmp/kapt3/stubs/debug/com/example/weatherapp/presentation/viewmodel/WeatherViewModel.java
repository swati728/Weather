package com.example.weatherapp.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001f"}, d2 = {"Lcom/example/weatherapp/presentation/viewmodel/WeatherViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/weatherapp/data/repository/WeatherRepository;", "(Lcom/example/weatherapp/data/repository/WeatherRepository;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/example/weatherapp/presentation/state/WeatherEvent;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/weatherapp/presentation/state/WeatherUiState;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "fetchJob", "Lkotlinx/coroutines/Job;", "searchQueryFlow", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadWeather", "", "city", "isPullToRefresh", "", "onIntent", "intent", "Lcom/example/weatherapp/presentation/state/WeatherIntent;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class WeatherViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.weatherapp.data.repository.WeatherRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.weatherapp.presentation.state.WeatherUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.weatherapp.presentation.state.WeatherUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.example.weatherapp.presentation.state.WeatherEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.example.weatherapp.presentation.state.WeatherEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> searchQueryFlow;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job fetchJob;
    
    @javax.inject.Inject()
    public WeatherViewModel(@org.jetbrains.annotations.NotNull()
    com.example.weatherapp.data.repository.WeatherRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.weatherapp.presentation.state.WeatherUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.example.weatherapp.presentation.state.WeatherEvent> getEvents() {
        return null;
    }
    
    /**
     * Single entry point for every user action -- the "intent" side of UDF.
     */
    public final void onIntent(@org.jetbrains.annotations.NotNull()
    com.example.weatherapp.presentation.state.WeatherIntent intent) {
    }
    
    private final void loadWeather(java.lang.String city, boolean isPullToRefresh) {
    }
}