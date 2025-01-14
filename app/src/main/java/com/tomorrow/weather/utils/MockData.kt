package com.tomorrow.weather.utils

import com.tomorrow.weather.data.local.CurrentWeather
import com.tomorrow.weather.data.local.HourlyForecast
import com.tomorrow.weather.data.local.NextDaysWeather
import com.tomorrow.weather.data.local.TodayWeather
import com.tomorrow.weather.data.local.WeatherForecast

fun getDummyWeatherForecast(): WeatherForecast {
    val currentWeather = CurrentWeather(
        temperature = -5,
        weatherCode = 1, // Clear sky
        minTemperature = 20,
        maxTemperature = 30,
        feelsLikeTemperature = 27,
        isDay = true,
        uvIndex = 5.5,
        humidity = 60,
        windSpeed = 5.0,
        windUnit = "m/s",
        dewPoint = 16.0,
        pressure = 1013.0,
        pressureUnit = "hPa",
        aqi = 50.0 // Good air quality
    )

    val hourlyForecast = (0..23).map { hour ->
        HourlyForecast(
            time = "2025-01-11T${hour.toString().padStart(2, '0')}:00",
            temperature = 20 + hour % 5, // Simulate temperature variation
            weatherCode = if (hour in 6..18) 800 else 801 // Day/Night weather
        )
    }

    val todayWeather = TodayWeather(
        date = "2025-01-11",
        dayName = "Monday",
        hourlyForecast = hourlyForecast
    )

    val nextDaysWeather = (1..7).map { day ->
        NextDaysWeather(
            date = "2025-01-${(11 + day).toString().padStart(2, '0')}",
            dayName = listOf("Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday")[day % 7],
            dayTemperature = 25 + day,
            dayWeatherCode = 800 + day % 3, // Simulate different weather codes
            nightTemperature = 15 + day,
            nightWeatherCode = 801 + day % 2
        )
    }

    return WeatherForecast(
        latitude = 52.5200, // Latitude of Berlin
        longitude = 13.4050, // Longitude of Berlin
        currentWeather = currentWeather,
        today = todayWeather,
        nextDays = nextDaysWeather
    )
}
