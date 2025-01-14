package com.tomorrow.weather.ui.weather

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tomorrow.weather.R
import com.tomorrow.weather.data.local.entity.WeatherForecast
import com.tomorrow.weather.ui.weather.component.NextDaysComponent
import com.tomorrow.weather.ui.weather.component.TodayComponent
import com.tomorrow.weather.ui.weather.component.WeatherImage
import com.tomorrow.weather.ui.weather.component.WeatherTopAppBar
import com.tomorrow.weather.ui.weather.state.WeatherError
import com.tomorrow.weather.ui.weather.state.WeatherUiState
import com.tomorrow.weather.utils.getDummyWeatherForecast


@Composable
fun WeatherScreen(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {

    val uiState by weatherViewModel.uiState.collectAsStateWithLifecycle()

    manageLocationUpdates(LocalLifecycleOwner.current, weatherViewModel)

    Scaffold(
        topBar = {
            WeatherTopAppBar(openDrawer = openDrawer)
        },
        modifier = modifier,
        content = { innerPadding ->

            when (uiState) {
                is WeatherUiState.HasWeatherInfo -> {
                    WeatherScreenContent(
                        weatherInfo = (uiState as WeatherUiState.HasWeatherInfo).weatherForecast,
                        modifier = modifier,
                        contentPadding = innerPadding,
                    )
                }

                is WeatherUiState.NoWeatherInfo -> {
                    Log.e("WeatherScreen", "NoWeatherInfo: ${uiState.weatherError}")
                    NoWeatherScreenContent(uiState, weatherViewModel)
                }

            }

        }
    )

}

@Composable
private fun manageLocationUpdates(
    lifecycleOwner: LifecycleOwner,
    weatherViewModel: WeatherViewModel
) {
    // Observing lifecycle events
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE || event == Lifecycle.Event.ON_RESUME) {
                weatherViewModel.startFetchingWeather()
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                weatherViewModel.stopFetchingWeather()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun NoWeatherScreenContent(uiState: WeatherUiState, weatherViewModel: WeatherViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (uiState.isLoading) {
            Text(
                text = stringResource(R.string.loading),
                modifier = Modifier.padding(16.dp)
            )
        } else if (uiState.weatherError != null) {
            weatherViewModel.stopFetchingWeather()

            if (uiState.weatherError is WeatherError.NetworkError)
            Image(
                painter = painterResource(R.drawable.ic_connection_error),
                contentDescription = stringResource(R.string.error_no_internet),
                modifier = Modifier.padding(16.dp).size(100.dp)
            )

            val errorMessage = if (uiState.weatherError is WeatherError.NetworkError)
                stringResource(R.string.error_no_internet)
            else
                stringResource(R.string.error_unknown)

            Text(
                text = errorMessage,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    weatherViewModel.startFetchingWeather()
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun WeatherScreenContent(
    weatherInfo: WeatherForecast,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.purple_background_start),
                        colorResource(R.color.purple_background_end)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WeatherInfo(
            weatherInfo = weatherInfo,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WeatherInfo(
    weatherInfo: WeatherForecast,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {

        item { // Current weather

            Text(
                text = "${weatherInfo.latitude}, ${weatherInfo.longitude}",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                WeatherImage(
                    modifier = modifier,
                    weatherCode = weatherInfo.currentWeather.weatherCode,
                    size = 64,
                    isDay = weatherInfo.currentWeather.isDay
                )

                Text(
                    text = "${weatherInfo.currentWeather.temperature}",
                    modifier = Modifier
                        .padding(16.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 72.sp,
                )
            }

            Text(
                text = stringResource(
                    id = R.string.weather_info,
                    weatherInfo.currentWeather.maxTemperature.toString(),
                    weatherInfo.currentWeather.minTemperature.toString(),
                    weatherInfo.currentWeather.feelsLikeTemperature.toString()
                ),
                color = colorResource(R.color.white),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { // Today's Weather
            Spacer(Modifier.height(16.dp))
            TodayComponent(
                modifier = modifier,
                today = weatherInfo.today,
            )
        }

        item { // Next Days Weather
            Spacer(Modifier.height(16.dp))
            NextDaysComponent(
                modifier = modifier,
                nextDays = weatherInfo.nextDays,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenContentPreview() {
    WeatherScreenContent(
        weatherInfo = getDummyWeatherForecast(),
        modifier = Modifier,
        contentPadding = PaddingValues(0.dp),
    )
}
