package com.skycom.weatherapp.feature.weatherDetails.data

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.core.network.makeApiCall
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.core.network.model.RawWeatherForecastResponse
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsDataSource
import javax.inject.Inject

class DefaultWeatherDetailsDataSource @Inject constructor(
    private val api: OpenWeatherApi,
) : WeatherDetailsDataSource {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
    ): ResultWrapper<RawCurrentWeatherResponse> = makeApiCall {
        api.getCurrentWeather(
            lat,
            lon
        )
    }

    override suspend fun getForecastWeather(
        lat: Double,
        lon: Double,
    ): ResultWrapper<RawWeatherForecastResponse> = makeApiCall {
        api.getWeatherForecast(
            lat,
            lon
        )
    }

}