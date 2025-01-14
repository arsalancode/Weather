package com.tomorrow.weather.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Main Response Object
data class DTOWeatherResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Int,
    @SerializedName("current") val current: CurrentWeatherData, // Added current data
    @SerializedName("hourly") val hourly: HourlyData,
    @SerializedName("daily") val daily: DailyData
) : Serializable

// Current Weather Data (Current Weather Parameters)
data class CurrentWeatherData(
    @SerializedName("temperature_2m") val temperature2m: Double, // Current temperature
    @SerializedName("relative_humidity_2m") val humidity2m: Double, // Current humidity
    @SerializedName("apparent_temperature") val apparentTemperature: Double, // "Feels like" temperature
    @SerializedName("is_day") val isDay: Int, // Day or night indicator
    @SerializedName("precipitation") val precipitation: Double, // Precipitation amount
    @SerializedName("rain") val rain: Double, // Rain amount
    @SerializedName("showers") val showers: Double, // Showers amount
    @SerializedName("snowfall") val snowfall: Double, // Snowfall amount
    @SerializedName("weather_code") val weatherCode: Int, // Weather code
    @SerializedName("cloud_cover") val cloudCover: Double, // Cloud cover percentage
    @SerializedName("pressure_msl") val pressureMsl: Double, // Pressure at mean sea level
    @SerializedName("surface_pressure") val surfacePressure: Double, // Surface atmospheric pressure
    @SerializedName("wind_speed_10m") val windSpeed10m: Double, // Wind speed at 10m
    @SerializedName("wind_direction_10m") val windDirection10m: Double, // Wind direction at 10m
    @SerializedName("wind_gusts_10m") val windGusts10m: Double // Wind gusts at 10m
) : Serializable

// Hourly Data (Next 24 Hours)
data class HourlyData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature2m: List<Double>,
    @SerializedName("apparent_temperature") val apparentTemperature: List<Double>,
    @SerializedName("precipitation_probability") val precipitationProbability: List<Double>, // Precipitation probability
    @SerializedName("precipitation") val precipitation: List<Double>,
    @SerializedName("rain") val rain: List<Double>,
    @SerializedName("showers") val showers: List<Double>,
    @SerializedName("snowfall") val snowfall: List<Double>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
    @SerializedName("dew_point_2m") val dewPoint2m: List<Double>, // Add dew point field here
    @SerializedName("humidity_2m") val humidity2m: List<Double>, // Add humidity field here
    @SerializedName("cloud_cover") val cloudCover: List<Double>,
    @SerializedName("cloud_cover_low") val cloudCoverLow: List<Double>, // Low cloud cover
    @SerializedName("cloud_cover_mid") val cloudCoverMid: List<Double>, // Mid cloud cover
    @SerializedName("cloud_cover_high") val cloudCoverHigh: List<Double>, // High cloud cover
    @SerializedName("visibility") val visibility: List<Double>, // Visibility
    @SerializedName("wind_speed_10m") val windSpeed10m: List<Double>,
    @SerializedName("uv_index") val uvIndex: List<Double>, // UV index
    @SerializedName("uv_index_clear_sky") val uvIndexClearSky: List<Double>, // UV index under clear sky
    @SerializedName("is_day") val isDay: List<Int>, // Day or night for each hour
    @SerializedName("sunshine_duration") val sunshineDuration: List<Double> // Sunshine duration
) : Serializable

// Daily Data (Next 6 Days)
data class DailyData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperatureMin: List<Double>,
    @SerializedName("precipitation_sum") val precipitationSum: List<Double>, // Total precipitation
    @SerializedName("rain_sum") val rainSum: List<Double>, // Total rain
    @SerializedName("showers_sum") val showersSum: List<Double>, // Total showers
    @SerializedName("weather_code") val weatherCode: List<Int>
) : Serializable
