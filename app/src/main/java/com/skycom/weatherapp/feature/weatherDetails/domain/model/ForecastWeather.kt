package com.skycom.weatherapp.feature.weatherDetails.domain.model

data class ForecastWeather(
    val icon: String,
    val dateTime: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val precipitationProbability: Double,
    val weather: Weather,
)