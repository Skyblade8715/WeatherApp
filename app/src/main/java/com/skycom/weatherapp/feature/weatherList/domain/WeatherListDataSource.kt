package com.skycom.weatherapp.feature.weatherList.domain

import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.ResultWrapper
import retrofit2.Response

interface WeatherListDataSource {
    suspend fun searchCity(cityName: String): ResultWrapper<List<RawCityLocationResponse>>

}