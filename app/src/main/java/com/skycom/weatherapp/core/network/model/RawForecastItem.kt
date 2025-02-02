package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawForecastItem(
    @SerializedName("dt_txt") val dateTime: String,
    val main: RawMainWeather,
    val weather: List<RawWeatherDescription>
)
