package com.skycom.weatherapp.feature.weatherList.data.repository

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.model.CityWeatherInfo
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListLocalDataSource
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRemoteDataSource
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRepository
import javax.inject.Inject

class DefaultWeatherListRepository @Inject constructor(
    private val remoteDataSource: WeatherListRemoteDataSource,
    private val localDataSource: WeatherListLocalDataSource
) : WeatherListRepository {

    override suspend fun searchCity(cityName: String): ResultWrapper<List<CityLocation>> {
        val geoResult = remoteDataSource.searchCity(cityName)
        if (geoResult is ResultWrapper.Success) {
            val cityLocations = geoResult.data.map { rawCity ->
                CityLocation(
                    name = rawCity.name,
                    country = rawCity.country,
                    latitude = rawCity.latitude,
                    longitude = rawCity.longitude
                )
            }
            return ResultWrapper.Success(cityLocations)
        }
        return geoResult as ResultWrapper.Error
    }

    override suspend fun getWeatherForCity(city: CityLocation): ResultWrapper<CityWeatherInfo> {
        val weatherResult = remoteDataSource.getWeather(city.latitude, city.longitude)
        return if (weatherResult is ResultWrapper.Success) {
            val weather = weatherResult.data
            ResultWrapper.Success(
                CityWeatherInfo(
                    name = city.name,
                    country = city.country,
                    temperature = weather.main.temperature,
                    weatherDescription = weather.weather.firstOrNull()?.description
                        ?: "Unknown",
                    weatherIcon = weather.weather.firstOrNull()?.icon ?: ""
                )
            )
        } else {
            weatherResult as ResultWrapper.Error
        }
    }

    override suspend fun getRecentSearches(): List<CityLocation> {
        return localDataSource.getSavedCities()
    }

    override suspend fun saveCitySearch(cityLocationInfo: CityLocation) {
        localDataSource.saveSearchedCity(cityLocationInfo)
    }
}
