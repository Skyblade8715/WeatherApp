package com.skycom.weatherapp.feature.weatherList.domain.usecases

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRepository
import com.skycom.weatherapp.feature.weatherList.ui.model.CityWeatherDisplay
import javax.inject.Inject

class GetRecentSearchesUseCase @Inject constructor(
    private val repository: WeatherListRepository,
) {
    suspend operator fun invoke(): ResultWrapper<List<CityWeatherDisplay>> {
        val recentCities = repository.getRecentSearches()
        if (recentCities.isEmpty()) return ResultWrapper.Success(emptyList())

        val updatedCities = recentCities.mapNotNull { city ->
            val weatherResult = repository.getWeatherForCity(city)
            if (weatherResult is ResultWrapper.Success) {
                val weather = weatherResult.data
                CityWeatherDisplay(
                    CityLocation(
                        name = city.name,
                        country = city.country,
                        latitude = city.latitude,
                        longitude = city.longitude,
                    ),
                    temperature = weather.temperature,
                    weatherDescription = weather.weatherDescription,
                    weatherIcon = weather.weatherIcon,
                )
            } else null
        }
        return ResultWrapper.Success(updatedCities)
    }
}
