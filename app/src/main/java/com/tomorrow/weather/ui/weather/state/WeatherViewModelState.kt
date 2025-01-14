package com.tomorrow.weather.ui.weather.state

import com.tomorrow.weather.data.local.entity.WeatherForecast

/**
 * An internal representation of the Home route state, in a raw form
 */
data class WeatherViewModelState(
    val weatherForecast: WeatherForecast?,
    val isLoading: Boolean = false,
    val weatherError: WeatherError? = null,
) {

    /**
     * Converts this [WeatherViewModelState] into a more strongly typed [WeatherUiState] for driving
     * the ui.
     */
    fun toUiState(): WeatherUiState =
        if (weatherForecast == null) {
            WeatherUiState.NoWeatherInfo(
                isLoading = isLoading,
                weatherError = weatherError,
            )
        } else {
            WeatherUiState.HasWeatherInfo(
                weatherForecast = weatherForecast,
                isLoading = isLoading,
                weatherError = null,
            )
        }
}
