package com.tomorrow.weather.domain.usecase

import com.tomorrow.weather.data.local.entity.WeatherForecast
import com.tomorrow.weather.domain.mapper.toWeatherForecast
import com.tomorrow.weather.domain.repository.RemoteWeatherRepository
import com.tomorrow.weather.utils.isNoInternetException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import javax.inject.Inject

class GetWeatherInfoUseCase @Inject constructor(
    private val remoteWeatherRepository: RemoteWeatherRepository,
) {
    companion object {
        private const val RETRY_COUNT = 2L // if internet issue then try 2 times more
        private const val RETRY_DELAY_MS = 1000L // 1 second
    }

    operator fun invoke(latitude: Double, longitude: Double): Flow<WeatherForecast> =
        flow {
            val response = remoteWeatherRepository.getWeatherInfo(latitude, longitude)
            emit(response.toWeatherForecast())
        }
            .retryWhen { exception, attempt ->
                if (attempt < RETRY_COUNT && exception.isNoInternetException()) {
                    delay(RETRY_DELAY_MS) // Add delay before retrying
                    true // Continue retrying
                } else {
                    false // Stop retrying
                }
            }
}
