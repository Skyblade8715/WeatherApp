package com.skycom.weatherapp.feature.weatherList.domain.usecases

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRepository
import com.skycom.weatherapp.feature.weatherList.ui.model.CityWeatherDisplay
import com.skycom.weatherapp.feature.weatherList.ui.model.getTemperatureColor
import javax.inject.Inject

class SearchCitiesWithWeatherUseCase @Inject constructor(
    private val repository: WeatherListRepository
) {
    suspend operator fun invoke(cityName: String): ResultWrapper<List<CityWeatherDisplay>> {
        val searchResult = repository.searchCity(cityName)
        if (searchResult !is ResultWrapper.Success) return searchResult as ResultWrapper.Error

        val cities = searchResult.data
        val citiesWithWeather = cities.mapNotNull { city ->
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
                    temperatureDisplay = "${weather.temperature.toInt()}°C",
                    weatherDescription = weather.weatherDescription,
                    weatherIcon = weather.weatherIcon,
                    temperatureColor = getTemperatureColor(weather.temperature.toInt())
                )
            } else null
        }
        return ResultWrapper.Success(citiesWithWeather)
    }
}