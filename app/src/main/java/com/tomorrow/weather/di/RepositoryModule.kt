package com.tomorrow.weather.di

import com.tomorrow.weather.data.remote.WeatherApi
import com.tomorrow.weather.data.remote.repo.RemoteWeatherRepositoryImpl
import com.tomorrow.weather.domain.repository.RemoteWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /*
     * The method returns the RemoteFlagRepositoryImpl instance
     */
    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): RemoteWeatherRepository =
        RemoteWeatherRepositoryImpl(weatherApi)


}