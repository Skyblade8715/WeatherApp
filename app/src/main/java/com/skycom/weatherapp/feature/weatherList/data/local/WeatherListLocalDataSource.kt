package com.skycom.weatherapp.feature.weatherList.data.local

import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListLocalDataSource
import javax.inject.Inject

class DefaultWeatherListLocalDataSource @Inject constructor(
    private val weatherListDao: WeatherListDao
) : WeatherListLocalDataSource {


    override suspend fun getSavedCities(): List<CityLocation> {
        return weatherListDao.getSavedCities().map {
            CityLocation(it.name, it.country, it.latitude, it.longitude)
        }
    }

    override suspend fun saveSearchedCity(cityLocation: CityLocation) {
        val entity = SearchedCityEntity(
            name = cityLocation.name,
            country = cityLocation.country,
            latitude = cityLocation.latitude,
            longitude = cityLocation.longitude
        )
        weatherListDao.insertCity(entity)
    }
}
