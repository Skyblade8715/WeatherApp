package com.skycom.weatherapp.feature.weatherList.domain.repository

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.model.CityWeatherInfo

interface WeatherListRepository {
    suspend fun searchCity(cityName: String): ResultWrapper<List<CityLocation>>
    suspend fun getWeatherForCity(city: CityLocation): ResultWrapper<CityWeatherInfo>
    suspend fun getRecentSearches(): List<CityLocation>
    suspend fun saveCitySearch(cityLocationInfo: CityLocation)
}