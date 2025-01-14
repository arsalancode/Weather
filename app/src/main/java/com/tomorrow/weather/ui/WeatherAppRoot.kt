package com.tomorrow.weather.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tomorrow.weather.ui.WeatherDestinations.HOME_ROUTE
import com.tomorrow.weather.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch


@Composable
fun WeatherAppRoot() {

    WeatherAppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            WeatherNavigationActions(navController)
        }
        val coroutineScope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: HOME_ROUTE
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToHistory = navigationActions.navigateToHistory,
                    drawerState = drawerState,
                    closeDrawer = { coroutineScope.launch { drawerState.close() } },
                )
            },
            drawerState = drawerState,
            gesturesEnabled = true
        ) {
            Row {
                WeatherAppNavGraph(
                    navController = navController,
                    openDrawer = { coroutineScope.launch { drawerState.open() } }
                )
            }

        }


    }

}