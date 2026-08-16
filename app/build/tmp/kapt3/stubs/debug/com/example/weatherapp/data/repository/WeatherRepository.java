package com.example.weatherapp.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/weatherapp/data/repository/WeatherRepository;", "", "api", "Lcom/example/weatherapp/data/remote/WeatherApiService;", "dao", "Lcom/example/weatherapp/data/local/WeatherDao;", "(Lcom/example/weatherapp/data/remote/WeatherApiService;Lcom/example/weatherapp/data/local/WeatherDao;)V", "fetchWeather", "Lcom/example/weatherapp/data/repository/Resource;", "Lcom/example/weatherapp/domain/model/WeatherInfo;", "city", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCachedWeather", "app_debug"})
public final class WeatherRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.weatherapp.data.remote.WeatherApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.weatherapp.data.local.WeatherDao dao = null;
    
    @javax.inject.Inject()
    public WeatherRepository(@org.jetbrains.annotations.NotNull()
    com.example.weatherapp.data.remote.WeatherApiService api, @org.jetbrains.annotations.NotNull()
    com.example.weatherapp.data.local.WeatherDao dao) {
        super();
    }
    
    /**
     * Network-first with cache fallback:
     * 1. Try the network so the user always sees fresh data when possible.
     * 2. On success, persist to Room (this becomes the resilient local cache).
     * 3. On any network failure, fall back to the last cached value instead
     *   of showing a dead end -- this is what keeps the app usable offline.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchWeather(@org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.weatherapp.data.repository.Resource<com.example.weatherapp.domain.model.WeatherInfo>> $completion) {
        return null;
    }
    
    /**
     * Emits whatever is currently cached, immediately, for instant cold-start UI.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCachedWeather(@org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.weatherapp.domain.model.WeatherInfo> $completion) {
        return null;
    }
}