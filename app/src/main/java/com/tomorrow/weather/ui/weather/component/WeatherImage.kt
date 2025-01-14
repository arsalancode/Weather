package com.tomorrow.weather.ui.weather.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomorrow.weather.R

@Composable
fun WeatherImage(
    modifier: Modifier = Modifier,
    weatherCode: Int,
    isDay: Boolean = true,
    size: Int = 32
) {
    Image(
        modifier = modifier.size(size.dp).padding(4.dp),
        painter = painterResource(id = getWeatherIcon(weatherCode, isDay)),
        contentDescription = null,
    )
}

fun getWeatherIcon(code: Int, isDay: Boolean = true): Int {
    Log.i("WeatherImage", "code: $code")

    return when (code) {
        0 -> if (isDay) R.drawable.ic_sunny else R.drawable.ic_clear_sky_night //R.drawable.icon_clear_sky
        1 -> if (isDay) R.drawable.ic_sunny else R.drawable.ic_clear_sky_night //R.drawable.icon_mainly_clear
        2 -> if(isDay) R.drawable.ic_sunny_cloudy else R.drawable.ic_cloudy // R.drawable.icon_partly_cloudy
        3 -> R.drawable.ic_cloudy //
        45, 48 -> R.drawable.ic_cloudy // fog
        51, 53, 55 -> R.drawable.ic_cloudy // R.drawable.icon_drizzle
        61, 63, 65 -> R.drawable.ic_heavy_rain //R.drawable.icon_rain
        71, 73, 75 -> R.drawable.ic_snow
        80, 81, 82 -> R.drawable.ic_rain //R.drawable.icon_rain_shower
        85, 86 -> R.drawable.ic_rain
        95, 96, 99 -> R.drawable.ic_heavy_rain //R.drawable.icon_thunderstorm
        100 -> R.drawable.ic_humidity //R.drawable.icon_snow
        else -> R.drawable.ic_app_icon //R.drawable.icon_unknown
    }
}


//fun getWeatherIcon(code: Int): Int {
//    return when (code) {
//        0 -> R.mipmap.ic_sunny //R.drawable.icon_clear_sky //R.drawable.icon_clear_sky
//        1 -> R.mipmap.ic_sunny //R.drawable.icon_mainly_clear
//        2 -> R.mipmap.ic_sunny_cloudy // R.drawable.icon_partly_cloudy
////        3 -> R.drawable.icon_overcast
////        45, 48 -> R.drawable.icon_fog
////        51, 53, 55 -> R.drawable.icon_drizzle
//        61, 63, 65 -> R.mipmap.ic_heavy_rain //R.drawable.icon_rain
////        71, 73, 75 -> R.drawable.icon_snow
//        80, 81, 82 -> R.mipmap.ic_rain //R.drawable.icon_rain_shower
////        85, 86 -> R.drawable.icon_snow_shower
//        95, 96, 99 -> R.mipmap.ic_heavy_rain //R.drawable.icon_thunderstorm
//        else -> R.mipmap.ic_app_icon //R.drawable.icon_unknown
//    }
//}

@Composable
@Preview
fun WeatherIconPreview(){
    WeatherImage(weatherCode = 12)
}