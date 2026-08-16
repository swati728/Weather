package com.example.weatherapp.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\u000eH\u00c6\u0003Jc\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\u000bH\u00d6\u0001J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016\u00a8\u0006,"}, d2 = {"Lcom/example/weatherapp/data/local/WeatherEntity;", "", "cityKey", "", "cityName", "temperatureC", "", "feelsLikeC", "condition", "conditionIconUrl", "humidity", "", "windKph", "lastUpdatedEpochMillis", "", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;IDJ)V", "getCityKey", "()Ljava/lang/String;", "getCityName", "getCondition", "getConditionIconUrl", "getFeelsLikeC", "()D", "getHumidity", "()I", "getLastUpdatedEpochMillis", "()J", "getTemperatureC", "getWindKph", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "weather_cache")
public final class WeatherEntity {
    @androidx.room.PrimaryKey()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cityKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cityName = null;
    private final double temperatureC = 0.0;
    private final double feelsLikeC = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String condition = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String conditionIconUrl = null;
    private final int humidity = 0;
    private final double windKph = 0.0;
    private final long lastUpdatedEpochMillis = 0L;
    
    public WeatherEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String cityKey, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName, double temperatureC, double feelsLikeC, @org.jetbrains.annotations.NotNull()
    java.lang.String condition, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionIconUrl, int humidity, double windKph, long lastUpdatedEpochMillis) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCityKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCityName() {
        return null;
    }
    
    public final double getTemperatureC() {
        return 0.0;
    }
    
    public final double getFeelsLikeC() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCondition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConditionIconUrl() {
        return null;
    }
    
    public final int getHumidity() {
        return 0;
    }
    
    public final double getWindKph() {
        return 0.0;
    }
    
    public final long getLastUpdatedEpochMillis() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.weatherapp.data.local.WeatherEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String cityKey, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName, double temperatureC, double feelsLikeC, @org.jetbrains.annotations.NotNull()
    java.lang.String condition, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionIconUrl, int humidity, double windKph, long lastUpdatedEpochMillis) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}