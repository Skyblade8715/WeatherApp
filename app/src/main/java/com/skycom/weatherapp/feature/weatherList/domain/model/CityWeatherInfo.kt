package com.skycom.weatherapp.feature.weatherList.domain.model

data class CityWeatherInfo(
    val name: String,
    val country: String,
    val temperature: Double,
    val weatherDescription: String,
    val weatherIcon: String,
)