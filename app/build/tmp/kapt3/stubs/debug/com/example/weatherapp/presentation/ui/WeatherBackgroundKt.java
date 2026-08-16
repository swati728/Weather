package com.example.weatherapp.presentation.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003\u001a\b\u0010\u0006\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0007\u001a\u00020\u0001H\u0003\u001a\u001a\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a8\u0006\u0012"}, d2 = {"RainEffect", "", "dropCount", "", "speedMultiplier", "", "SnowEffect", "SunEffect", "WeatherBackground", "gradient", "Lcom/example/weatherapp/presentation/ui/WeatherGradient;", "modifier", "Landroidx/compose/ui/Modifier;", "getWeatherGradient", "condition", "", "isDay", "", "app_debug"})
public final class WeatherBackgroundKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.example.weatherapp.presentation.ui.WeatherGradient getWeatherGradient(@org.jetbrains.annotations.NotNull()
    java.lang.String condition, boolean isDay) {
        return null;
    }
    
    /**
     * Full animated background: gradient base + weather-specific particle/light effects,
     * inspired by the layered look of Apple Weather.
     */
    @androidx.compose.runtime.Composable()
    public static final void WeatherBackground(@org.jetbrains.annotations.NotNull()
    com.example.weatherapp.presentation.ui.WeatherGradient gradient, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SunEffect() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RainEffect(int dropCount, float speedMultiplier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SnowEffect() {
    }
}