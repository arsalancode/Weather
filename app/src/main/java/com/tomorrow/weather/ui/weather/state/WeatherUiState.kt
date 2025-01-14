package com.tomorrow.weather.ui.weather.state

import com.tomorrow.weather.data.local.WeatherForecast

sealed interface WeatherUiState {
    val isLoading: Boolean
    val weatherError: WeatherError?

    data class NoWeatherInfo(
        override val isLoading: Boolean,
        override val weatherError: WeatherError?,
    ) : WeatherUiState

    data class HasWeatherInfo(
        val weatherForecast: WeatherForecast,
        override val isLoading: Boolean,
        override val weatherError: WeatherError?
    ) : WeatherUiState
}