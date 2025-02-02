package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawCurrentWeatherResponse(
    val main: RawMainWeather,
    val weather: List<RawWeatherDescription>,
    @SerializedName("wind_speed") val windSpeed: Double
)