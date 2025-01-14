package com.tomorrow.weather.ui.weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomorrow.weather.data.local.entity.NextDaysWeather
import com.tomorrow.weather.utils.getDayName

@Composable
fun ForeCastDayComponent(
    modifier: Modifier = Modifier,
    nextDaysWeather: NextDaysWeather
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = getDayName(nextDaysWeather.date),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
            )

            Row(
                modifier = Modifier.weight(2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                WeatherImage(
                    modifier = modifier.align(Alignment.CenterVertically),
                    weatherCode = nextDaysWeather.dayWeatherCode,
                    isDay = true
                )

                WeatherImage(
                    modifier = modifier.align(Alignment.CenterVertically),
                    weatherCode = nextDaysWeather.nightWeatherCode,
                    isDay = false
                )

                Text(
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                    text = "${nextDaysWeather.dayTemperature}°",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Text(
                    text = "${nextDaysWeather.nightTemperature}°",
                    style = MaterialTheme.typography.bodyMedium,
                )

            }
        }
    }
}