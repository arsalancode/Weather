package com.tomorrow.weather.ui.weather.state

sealed class WeatherError {
    object NetworkError : WeatherError()
    data class UnknownError(val message: String?) : WeatherError()
}
