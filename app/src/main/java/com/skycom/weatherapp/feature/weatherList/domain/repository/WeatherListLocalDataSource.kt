package com.skycom.weatherapp.feature.weatherList.domain.repository

import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation

interface WeatherListLocalDataSource {
    suspend fun getSavedCities(): List<CityLocation>
    suspend fun saveSearchedCity(cityLocation: CityLocation)
}