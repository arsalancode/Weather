package com.tomorrow.weather.ui.weather.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.tomorrow.weather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    openDrawer: () -> Unit,
) {

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.home_screen_title))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.cd_open_navigation_drawer)
                )
            }
        },
    )
}