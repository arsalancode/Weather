package com.tomorrow.weather.di

import com.tomorrow.weather.data.remote.WeatherApi
import com.tomorrow.weather.data.remote.WeatherApi.ApiURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val STANDARD_TIME_OUT_IN_SECONDS = 40L


    /**
     * Provides BaseUrl as string
     */
    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return ApiURL.BASE_URL
    }
    /**
     * Provides LoggingInterceptor for api information
     */
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(STANDARD_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(STANDARD_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.readTimeout(STANDARD_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(STANDARD_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    /**
     * Provides converter factory for retrofit
     */
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
    /**
     * Provides Api Service using retrofit
     */
    @Singleton
    @Provides
    fun provideRestApiService(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

}