package com.skycom.weatherapp.feature.weatherDetails.domain.model

data class Weather(
    val description: String,
    val temperature: Double,
    val feelsLikeTemperature: Double,
    val pressure: Int,
    val humidity: Int,
)