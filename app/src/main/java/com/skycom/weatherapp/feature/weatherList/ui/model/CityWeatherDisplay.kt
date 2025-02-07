package com.skycom.weatherapp.feature.weatherList.ui.model

import com.skycom.weatherapp.core.common.model.CityLocation

data class CityWeatherDisplay(
    val location: CityLocation,
    val temperature: Double,
    val weatherDescription: String,
    val weatherIcon: String,
)

