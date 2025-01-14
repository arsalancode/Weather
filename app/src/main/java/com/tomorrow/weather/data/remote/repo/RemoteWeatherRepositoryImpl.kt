package com.tomorrow.weather.data.remote.repo

import com.tomorrow.weather.data.remote.WeatherApi
import com.tomorrow.weather.data.remote.model.DTOWeatherResponse
import com.tomorrow.weather.domain.repository.RemoteWeatherRepository
import javax.inject.Inject

class RemoteWeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : RemoteWeatherRepository {

    override suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): DTOWeatherResponse = weatherApi.getWeatherForecast(latitude, longitude)

}