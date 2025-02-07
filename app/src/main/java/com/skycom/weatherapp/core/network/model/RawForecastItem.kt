package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawForecastItem(
    @SerializedName("dt") val dateTime: Long,
    val main: RawMainWeather,
    val weather: List<RawWeatherDescription>,
    val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("pop") val precipitationProbability: Double,
    @SerializedName("clouds_all") val cloudsPercentage: Double,
)
