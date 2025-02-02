package com.skycom.weatherapp.feature.weatherList.data.remote

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRemoteDataSource
import javax.inject.Inject

class DefaultWeatherListRemoteDataSource @Inject constructor(
    private val api: OpenWeatherApi,
) : WeatherListRemoteDataSource {

    override suspend fun searchCity(cityName: String): ResultWrapper<List<RawCityLocationResponse>> {
        return try {
            val response = api.getCityLocation(cityName)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultWrapper.Success(it)
                } ?: ResultWrapper.Error("Empty response body", response.code())
            } else {
                ResultWrapper.Error("API error: ${response.message()}", response.code())
            }
        } catch (e: Exception) {
            ResultWrapper.Error("Network error: ${e.localizedMessage}")
        }
    }

    override suspend fun getWeather(
        lat: Double,
        lon: Double
    ): ResultWrapper<RawCurrentWeatherResponse> {
        return try {
            val response = api.getCurrentWeather(lat, lon)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultWrapper.Success(it)
                } ?: ResultWrapper.Error("Empty response body", response.code())
            } else {
                ResultWrapper.Error("API error: ${response.message()}", response.code())
            }
        } catch (e: Exception) {
            ResultWrapper.Error("Network error: ${e.localizedMessage}")
        }
    }
}
