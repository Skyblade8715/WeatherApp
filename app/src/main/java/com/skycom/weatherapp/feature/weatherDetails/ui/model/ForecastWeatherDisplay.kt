package com.skycom.weatherapp.feature.weatherDetails.ui.model

data class ForecastWeatherDisplay(
    val date: String,
    val description: String,
    val icon: String,
    val perceivedTemperature: Double,
    val temperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val precipitation: Int,
)