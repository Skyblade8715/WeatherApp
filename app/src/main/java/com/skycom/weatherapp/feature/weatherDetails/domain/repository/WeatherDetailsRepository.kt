package com.skycom.weatherapp.feature.weatherDetails.domain.repository

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherDetails.domain.model.CurrentWeather
import com.skycom.weatherapp.feature.weatherDetails.domain.model.ForecastWeather

interface WeatherDetailsRepository {
    suspend fun getCurrentWeatherForCity(city: CityLocation): ResultWrapper<CurrentWeather>
    suspend fun getForecastWeatherForCity(city: CityLocation): ResultWrapper<List<ForecastWeather>>
}