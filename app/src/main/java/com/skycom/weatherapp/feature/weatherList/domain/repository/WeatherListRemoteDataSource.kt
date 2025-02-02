package com.skycom.weatherapp.feature.weatherList.domain.repository

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse

interface WeatherListRemoteDataSource {
    suspend fun searchCity(cityName: String): ResultWrapper<List<RawCityLocationResponse>>
    suspend fun getWeather(lat: Double, lon: Double): ResultWrapper<RawCurrentWeatherResponse>
}