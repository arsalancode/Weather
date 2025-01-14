package com.tomorrow.weather.di

import com.tomorrow.weather.data.MockLocationDataSource
import com.tomorrow.weather.domain.LocationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationDataSource(
    ): LocationDataSource = MockLocationDataSource()

}
