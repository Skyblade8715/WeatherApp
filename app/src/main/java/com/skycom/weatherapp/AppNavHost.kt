package com.skycom.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherDetails.ui.WeatherDetailsScreen
import com.skycom.weatherapp.feature.weatherList.ui.WeatherListScreen

const val LIST_SCREEN_ROUTE = "list_route"
const val DETAILS_SCREEN_ROUTE = "details_route"

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN_ROUTE
    ) {
        composable(LIST_SCREEN_ROUTE) {
            WeatherListScreen(
                navigateToDetails = { city: CityLocation ->
                    navController.navigate("$DETAILS_SCREEN_ROUTE/${city.name}/${city.country}/${city.latitude}/${city.longitude}")
                },
            )
        }
        composable(
            route = "$DETAILS_SCREEN_ROUTE/{cityName}/{cityCountry}/{cityLatitude}/{cityLongitude}"
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            val cityCountry = backStackEntry.arguments?.getString("cityCountry") ?: ""
            val cityLatitude = backStackEntry.arguments?.getString("cityLatitude")?.toDouble() ?: 0.0
            val cityLongitude = backStackEntry.arguments?.getString("cityLongitude")?.toDouble() ?: 0.0
            val city =
                CityLocation(
                    cityName,
                    cityCountry,
                    cityLatitude,
                    cityLongitude
                )
            WeatherDetailsScreen(
                city = city
            )
        }
    }

}