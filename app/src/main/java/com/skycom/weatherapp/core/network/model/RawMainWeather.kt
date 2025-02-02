package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawMainWeather(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int
)
