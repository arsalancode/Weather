package com.tomorrow.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomorrow.weather.domain.LocationDataSource
import com.tomorrow.weather.domain.usecase.GetWeatherInfoUseCase
import com.tomorrow.weather.ui.weather.state.WeatherError
import com.tomorrow.weather.ui.weather.state.WeatherViewModelState
import com.tomorrow.weather.utils.isNoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
    private val locationDataSource: LocationDataSource
) : ViewModel() {

    companion object {
        private const val TAG = "WeatherViewModel"
        const val LOCATION_DELAY_MS = 10000L // 10 seconds
    }

    private val _viewModelState = MutableStateFlow(
        WeatherViewModelState(
            isLoading = true,
            weatherError = null,
            weatherForecast = null,
        )
    )

    val uiState = _viewModelState
        .map(WeatherViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _viewModelState.value.toUiState()
        )

    private var userLocationsJob: Job? = null


    fun startFetchingWeather() {
        fetchWeatherForLocations(locationDataSource.getSimulatedLocations())
    }

    fun stopFetchingWeather() {
        userLocationsJob?.cancel()
        userLocationsJob = null
    }

    fun fetchWeatherForLocations(locations: List<Pair<Double, Double>>) {
        if (userLocationsJob != null)
            return

        userLocationsJob = viewModelScope.launch {
            var index = 0
            while (true) {
                val (lat, lng) = locations[index]
                index = (index + 1) % locations.size

                // Fetch weather for the current location
                getWeather(lat, lng)

                // Wait before moving to the next location
                delay(LOCATION_DELAY_MS)
            }
        }
    }

    private fun getWeather(lat: Double, lng: Double) {
        viewModelScope.launch {
            getWeatherInfoUseCase(lat, lng)
                .onStart {
                    _viewModelState.update {
                        it.copy(
                            isLoading = true,
                            weatherForecast = null,
                            weatherError = null
                        )
                    }
                }
                .catch { exception ->
                    exception.printStackTrace()
                    _viewModelState.update {
                        it.copy(
                            isLoading = false,
                            weatherForecast = null,
                            weatherError =
                            if (exception.isNoInternetException())
                                WeatherError.NetworkError
                            else
                                WeatherError.UnknownError(exception.message)
                        )
                    }
                }
                .collect { weatherInfo ->
                    _viewModelState.update {
                        it.copy(
                            isLoading = false,
                            weatherForecast = weatherInfo,
                            weatherError = null
                        )
                    }
                }
        }
    }

}