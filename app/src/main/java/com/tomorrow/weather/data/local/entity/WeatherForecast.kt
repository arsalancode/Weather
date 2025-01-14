package com.tomorrow.weather.data.local.entity

// Main Weather Data Grouping for UI
data class WeatherForecast(
    val latitude: Double,
    val longitude: Double,
    val currentWeather: CurrentWeather,
    val today: TodayWeather,
    val nextDays: List<NextDaysWeather>
)

// Current Weather
data class CurrentWeather(
    val temperature: Int, // Current temperature
    val weatherCode: Int, // Weather code
    val minTemperature: Int, // Min temperature
    val maxTemperature: Int, // Max temperature
    val feelsLikeTemperature: Int, // Feels like temperature
    val isDay: Boolean, // Day or night
    val uvIndex: Double, // UV Index
    val humidity: Int, // Humidity
    val windSpeed: Double, // Wind speed
    val windUnit: String, // Wind unit (m/s)
    val dewPoint: Double, // Dew point temperature
    val pressure: Double, // Atmospheric pressure
    val pressureUnit: String, // Pressure unit (hPa)
    val aqi: Double // Air Quality Index (AQI)
)

// Today's Weather (including hourly breakdown)
data class TodayWeather(
    val date: String, // Date in "YYYY-MM-DD" format
    val dayName: String, // Day name (e.g., Monday)
    val hourlyForecast: List<HourlyForecast> // Array of next 24 hours
)

// Hourly Forecast for Today
data class HourlyForecast(
    val time: String, // Hour (e.g., "2025-01-11T01:00")
    val temperature: Int, // Temperature at this hour
    val weatherCode: Int, // Weather code for this hour
)

// Next 7 Days Weather (including today)
data class NextDaysWeather(
    val date: String, // Date in "YYYY-MM-DD" format
    val dayName: String, // Day name (e.g., Monday)
//    val humidity: Int, // Average humidity for the day
    val dayTemperature: Int, // Day's maximum temperature
    val dayWeatherCode: Int, // Day weather code
    val nightTemperature: Int, // Night's minimum temperature
    val nightWeatherCode: Int // Night weather code
)
