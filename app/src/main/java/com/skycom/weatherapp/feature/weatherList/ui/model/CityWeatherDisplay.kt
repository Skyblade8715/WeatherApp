package com.skycom.weatherapp.feature.weatherList.ui.model

import androidx.compose.ui.graphics.Color
import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation

data class CityWeatherDisplay(
    val location: CityLocation,
    val temperatureDisplay: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val temperatureColor: Color,
)

fun getTemperatureColor(temp: Int): Color {
    return when {
        temp < 10 -> Color.Blue
        temp in 10..20 -> Color.Black
        else -> Color.Red
    }
}