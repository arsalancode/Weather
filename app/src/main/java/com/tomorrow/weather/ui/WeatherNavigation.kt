package com.tomorrow.weather.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tomorrow.weather.ui.WeatherDestinations.HOME_ROUTE
import com.tomorrow.weather.ui.WeatherDestinations.SYNC_HISTORY_ROUTE

/**
 * Destinations used in the [WeatherAppRoot].
 */

object WeatherDestinations {
    const val HOME_ROUTE = "home"
    const val SYNC_HISTORY_ROUTE = "sync_history"
}

/**
 * Models the navigation actions in the app.
 */
class WeatherNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToHistory: () -> Unit = {
        navController.navigate(SYNC_HISTORY_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
