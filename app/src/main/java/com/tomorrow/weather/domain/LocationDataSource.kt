package com.tomorrow.weather.domain

interface LocationDataSource {
    fun getSimulatedLocations(): List<Pair<Double, Double>>
}
