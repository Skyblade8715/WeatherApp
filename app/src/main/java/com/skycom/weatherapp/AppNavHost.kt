package com.skycom.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skycom.weatherapp.feature.weatherDetails.ui.WeatherDetailsScreen
import com.skycom.weatherapp.feature.weatherList.ui.WeatherListScreen

const val LIST_SCREEN_ROUTE = "list_route"
const val DETAILS_SCREEN_ROUTE = "details_route"

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LIST_SCREEN_ROUTE) {
        composable(LIST_SCREEN_ROUTE) {
            WeatherListScreen(
                navigateToDetails = { city: String -> navController.navigate("details/$city") },
            )
        }
        composable("$DETAILS_SCREEN_ROUTE/{cityName}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WeatherDetailsScreen(cityName)
        }
    }

}