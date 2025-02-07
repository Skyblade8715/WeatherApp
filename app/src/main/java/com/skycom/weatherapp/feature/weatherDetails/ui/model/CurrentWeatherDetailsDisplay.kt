package com.skycom.weatherapp.feature.weatherDetails.ui.model

data class CurrentWeatherDetailsDisplay(
    val description: String,
    val temperature: Double,
    val perceivedTemperature: Double,
    val windSpeed: Double,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int,
    val cloudiness: Int,
    val snowIntensity: Double,
    val rainIntensity: Double,
    val sunrise: String,
    val sunset: String,
)