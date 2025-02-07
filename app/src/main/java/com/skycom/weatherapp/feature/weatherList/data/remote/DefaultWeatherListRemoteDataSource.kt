package com.skycom.weatherapp.feature.weatherList.data.remote

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.core.network.makeApiCall
import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRemoteDataSource
import javax.inject.Inject

class DefaultWeatherListRemoteDataSource @Inject constructor(
    private val api: OpenWeatherApi,
) : WeatherListRemoteDataSource {

    override suspend fun searchCity(cityName: String): ResultWrapper<List<RawCityLocationResponse>> =
        makeApiCall { api.getCityLocation(cityName) }

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
    ): ResultWrapper<RawCurrentWeatherResponse> = makeApiCall {
        api.getCurrentWeather(
            lat,
            lon
        )
    }
}
