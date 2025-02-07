package com.skycom.weatherapp.feature.weatherDetails.domain.model

data class CurrentWeather(
    val rainIntensity1h: Double,
    val snowIntensity1h: Double,
    val visibility: Int,
    val windSpeed: Double,
    val sunrise: Long,
    val sunset: Long,
    val weather: Weather,
    val cloudsPercentage: Double,
)
