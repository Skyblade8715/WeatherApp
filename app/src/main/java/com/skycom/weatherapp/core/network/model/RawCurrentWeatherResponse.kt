package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawCurrentWeatherResponse(
    val main: RawMainWeather,
    val weather: List<RawWeatherDescription>,
    val visibility: Int,
    val rain: Rain? = null,
    val snow: Snow? = null,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    val sys: Sys,
)

data class Wind(
    @SerializedName("speed") val windSpeed: Double,
    val deg: Int
)

data class Clouds(
    @SerializedName("all") val cloudsPercentage: Double
)

data class Rain(
    @SerializedName("1h") val rainIntensity: Double = 0.0
)

data class Snow(
    @SerializedName("1h") val snowIntensity: Double = 0.0
)
data class Sys(
    val sunrise: Long,
    val sunset: Long
)