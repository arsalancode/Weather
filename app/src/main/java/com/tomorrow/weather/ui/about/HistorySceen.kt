package com.tomorrow.weather.ui.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomorrow.weather.R

@Composable
fun AboutScreen(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            AboutScreenAppBar(
                openDrawer = openDrawer
            )
        },
        modifier = modifier,
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Weather App 1.1",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )

            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreenAppBar(
    openDrawer: () -> Unit,
) {

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.about_screen_title))
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.cd_open_navigation_drawer)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(
        openDrawer = {},
        modifier = Modifier,
    )
}
