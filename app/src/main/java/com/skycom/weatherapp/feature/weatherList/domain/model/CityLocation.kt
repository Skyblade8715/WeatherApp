package com.skycom.weatherapp.feature.weatherList.domain.model

data class CityLocation(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)