package com.tomorrow.weather.data

import com.tomorrow.weather.domain.LocationDataSource

class MockLocationDataSource : LocationDataSource {
    override fun getSimulatedLocations(): List<Pair<Double, Double>> {
        return listOf(
            Pair(53.619653, 10.079969), // Location 1
            Pair(53.080917, 8.847533),  // Location 2
            Pair(52.378385, 9.794862),  // Location 3
            Pair(52.496385, 13.444041), // Location 4
            Pair(53.866865, 10.739542), // Location 5
            Pair(54.304540, 10.152741), // Location 6
            Pair(54.797277, 9.491039),  // Location 7
            Pair(52.426412, 10.821392), // Location 8
            Pair(53.542788, 8.613462),  // Location 9
            Pair(53.141598, 8.242565)   // Location 10
        )
    }
}
