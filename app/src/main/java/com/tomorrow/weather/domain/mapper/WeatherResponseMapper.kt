package com.tomorrow.weather.domain.mapper

import com.tomorrow.weather.data.local.entity.CurrentWeather
import com.tomorrow.weather.data.local.entity.HourlyForecast
import com.tomorrow.weather.data.local.entity.NextDaysWeather
import com.tomorrow.weather.data.local.entity.TodayWeather
import com.tomorrow.weather.data.local.entity.WeatherForecast
import com.tomorrow.weather.data.remote.model.DTOWeatherResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DTOWeatherResponse.toWeatherForecast(): WeatherForecast {
    val currentWeather = CurrentWeather(
        temperature = current.temperature2m.toInt(),
        weatherCode = current.weatherCode,
        minTemperature = daily.temperatureMin[0].toInt(),
        maxTemperature = daily.temperatureMax[0].toInt(),
        feelsLikeTemperature = current.apparentTemperature.toInt(),
        isDay = current.isDay == 1,
        uvIndex = hourly.uvIndex.firstOrNull() ?: 0.0,
        humidity = current.humidity2m.toInt(),
        windSpeed = current.windSpeed10m,
        windUnit = "km/h",
        dewPoint = hourly.dewPoint2m.firstOrNull() ?: 0.0,
        pressure = current.pressureMsl,
        pressureUnit = "hPa",
        aqi = 50.0 // Replace with actual AQI data if available
    )

    val today = TodayWeather(
        date = hourly.time.first().substring(0, 10),
        dayName = getDayNameFromDate(hourly.time.first()),
        hourlyForecast = hourly.time.take(24).mapIndexed { index, time ->
            HourlyForecast(
                time = time,
                temperature = hourly.temperature2m[index].toInt(),
                weatherCode = hourly.weatherCode[index],
            )
        }
    )

    val nextDays = daily.time.mapIndexed { index, date ->
        NextDaysWeather(
            date = date,
            dayName = getDayNameFromDate(date),
            dayTemperature = daily.temperatureMax[index].toInt(),
            dayWeatherCode = daily.weatherCode[index],
            nightTemperature = daily.temperatureMin[index].toInt(),
            nightWeatherCode = daily.weatherCode[index]
        )
    }

    return WeatherForecast(
        latitude = latitude,
        longitude = longitude,
        currentWeather = currentWeather,
        today = today,
        nextDays = nextDays
    )
}

// Helper function to extract the day name from date string
fun getDayNameFromDate(date: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateObj = sdf.parse(date)
    val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dayFormat.format(dateObj ?: Date())
}
