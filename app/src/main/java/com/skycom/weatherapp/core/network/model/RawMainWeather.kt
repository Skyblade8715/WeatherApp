package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawMainWeather(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
)
