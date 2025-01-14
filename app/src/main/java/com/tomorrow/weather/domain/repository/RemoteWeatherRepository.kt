package com.tomorrow.weather.domain.repository

import com.tomorrow.weather.data.remote.model.DTOWeatherResponse

interface RemoteWeatherRepository {
    suspend fun getWeatherInfo(lat: Double, lng: Double): DTOWeatherResponse
}