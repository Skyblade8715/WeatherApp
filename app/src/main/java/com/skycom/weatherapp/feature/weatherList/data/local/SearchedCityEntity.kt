package com.skycom.weatherapp.feature.weatherList.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searched_cities")
data class SearchedCityEntity(
    @PrimaryKey val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
