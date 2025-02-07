package com.skycom.weatherapp.core.network

import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.model.RawCityLocationResponse
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.core.network.model.RawWeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {

    @GET("geo/1.0/direct")
    suspend fun getCityLocation(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 5,
    ): Response<List<RawCityLocationResponse>>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
    ): Response<RawCurrentWeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
    ): Response<RawWeatherForecastResponse>
}

suspend fun <T> makeApiCall(
    apiCall: suspend () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = apiCall()
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
