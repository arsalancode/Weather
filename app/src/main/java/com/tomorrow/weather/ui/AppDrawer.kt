package com.tomorrow.weather.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tomorrow.weather.R
import com.tomorrow.weather.ui.WeatherDestinations.HOME_ROUTE
import com.tomorrow.weather.ui.WeatherDestinations.SYNC_HISTORY_ROUTE
import com.tomorrow.weather.ui.theme.WeatherAppTheme

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToHistory: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
    ) {

    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier,
    ) {
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.home_screen_title)) },
            selected = currentRoute == HOME_ROUTE,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.about_screen_title)) },
            selected = currentRoute == SYNC_HISTORY_ROUTE,
            onClick = {
                navigateToHistory()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }

}

@Preview ("Drawer Contents")
@Composable
fun PreviewAppDrawer(){
    WeatherAppTheme {
        AppDrawer(
            drawerState = rememberDrawerState(DrawerValue.Open),
            currentRoute = HOME_ROUTE,
            navigateToHome = {},
            navigateToHistory = {},
            closeDrawer = {}
        )
    }
}