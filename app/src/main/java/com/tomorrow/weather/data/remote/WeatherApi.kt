package com.tomorrow.weather.data.remote

import com.tomorrow.weather.data.remote.model.DTOWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object ApiURL {
        const val BASE_URL = "https://api.open-meteo.com/v1/"
    }

    /**
     * Fetches the weather forecast from the Open-Meteo API.
     *
     * @param latitude The geographical latitude of the location for which the forecast is requested (e.g., 53.619653).
     * @param longitude The geographical longitude of the location for which the forecast is requested (e.g., 10.079969).
     * @param hourly Specifies the hourly weather parameters to retrieve. By default, this includes:
     *               - `temperature_2m`: The air temperature at 2 meters above ground in degrees Celsius.
     *               - `apparent_temperature`: The "feels like" temperature considering wind and humidity.
     *               - `weathercode`: A code representing the weather condition (e.g., clear sky, rain, snow).
     * @param daily Specifies the daily weather parameters to retrieve. By default, this includes:
     *              - `temperature_2m_max`: The maximum temperature at 2 meters above ground in degrees Celsius.
     *              - `temperature_2m_min`: The minimum temperature at 2 meters above ground in degrees Celsius.
     *              - `weathercode`: A code representing the daily average weather condition (e.g., clear, rainy, snowy).
     * @param timezone Automatically adjusts the data to the requested time zone. By default, it uses the time zone of the provided latitude/longitude.
     * @return A [DTOWeatherResponse] object containing the weather data for the specified location.
     */
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        // Parameters for current weather data (e.g., temperature, humidity, etc.)
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m",
        // Parameters for hourly forecast data (e.g., temperature, precipitation, wind speed, etc.)
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,weather_code,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility,wind_speed_10m,uv_index,uv_index_clear_sky,is_day,sunshine_duration",
        // Parameters for daily forecast data (e.g., max and min temperatures, precipitation sums, etc.)
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,showers_sum",
        @Query("timezone") timezone: String = "auto"
    ): DTOWeatherResponse

}