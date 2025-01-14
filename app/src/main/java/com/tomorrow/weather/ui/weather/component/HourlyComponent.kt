package com.tomorrow.weather.ui.weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tomorrow.weather.data.local.HourlyForecast
import com.tomorrow.weather.utils.formatTimeTo12Hour
import com.tomorrow.weather.utils.isDay

@Composable
fun HourlyComponent(
    modifier: Modifier = Modifier,
    hourlyForecast: HourlyForecast
) {
    ElevatedCard(
        modifier = modifier.padding(end = 12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .widthIn(min = 50.dp)
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = formatTimeTo12Hour(hourlyForecast.time),
                style = MaterialTheme.typography.bodySmall,
            )

            WeatherImage(
                modifier = modifier,
                weatherCode = hourlyForecast.weatherCode,
                isDay = isDay(hourlyForecast.time)
            )

            Text(
                text = "${hourlyForecast.temperature}°",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}