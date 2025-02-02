package com.skycom.weatherapp.feature.weatherList.data

import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherList.domain.WeatherListDataSource
import javax.inject.Inject

class DefaultWeatherListDataSource @Inject constructor(
    private val api: OpenWeatherApi,
) : WeatherListDataSource {

    override suspend fun searchCity(
        cityName: String,
    ): ResultWrapper<List<RawCityLocationResponse>> {
        return try {
            val response = api.getCityLocation(cityName)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultWrapper.Success(it)
                } ?: ResultWrapper.Error(response.message(), response.code())
            } else {
                ResultWrapper.Error(response.message(), response.code())
            }
        } catch (e: Exception) {
            ResultWrapper.Error(e.localizedMessage)
        }
    }
}
