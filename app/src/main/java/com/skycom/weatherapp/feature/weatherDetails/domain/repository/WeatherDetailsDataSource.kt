package com.skycom.weatherapp.feature.weatherDetails.domain.repository

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.core.network.model.RawWeatherForecastResponse

interface WeatherDetailsDataSource {
    suspend fun getCurrentWeather(lat: Double, lon: Double): ResultWrapper<RawCurrentWeatherResponse>
    suspend fun getForecastWeather(lat: Double, lon: Double): ResultWrapper<RawWeatherForecastResponse>
}