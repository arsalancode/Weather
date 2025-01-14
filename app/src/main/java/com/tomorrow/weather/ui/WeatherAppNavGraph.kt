package com.tomorrow.weather.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tomorrow.weather.ui.about.AboutScreen
import com.tomorrow.weather.ui.weather.WeatherScreen

@Composable
fun WeatherAppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {},
    startDestination: String = WeatherDestinations.HOME_ROUTE
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(WeatherDestinations.HOME_ROUTE) { navBackStackEntry ->

            WeatherScreen(
                openDrawer = openDrawer,
                modifier = modifier,
            )

        }
        composable(WeatherDestinations.SYNC_HISTORY_ROUTE) {
            AboutScreen(
                openDrawer = openDrawer,
                modifier = modifier,
            )

            BackHandler {
                navController.navigateUp()
            }

        }
    }

}