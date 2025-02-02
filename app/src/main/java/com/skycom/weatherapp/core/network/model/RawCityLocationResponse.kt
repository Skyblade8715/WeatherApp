package com.skycom.weatherapp.core.network.model

import com.google.gson.annotations.SerializedName

data class RawCityLocationResponse(
    val name: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    val country: String
)