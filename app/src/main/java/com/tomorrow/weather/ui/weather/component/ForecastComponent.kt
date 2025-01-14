package com.tomorrow.weather.ui.weather.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomorrow.weather.R
import com.tomorrow.weather.data.local.NextDaysWeather

@Composable
fun ForecastComponent(
    modifier: Modifier = Modifier,
    nextDays: List<NextDaysWeather>,
) {
    Text(
        text = stringResource(R.string.forecast),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(horizontal = 16.dp),
        textAlign = TextAlign.Start,
        color = Color.White
    )
    Column(
        modifier = modifier
    )
    {
        nextDays.forEach {
            ForeCastDayComponent(
                modifier = modifier,
                nextDaysWeather = it
            )
        }
    }
}