package com.skycom.weatherapp.feature.weatherDetails.ui.model

import com.skycom.weatherapp.core.common.model.ResultWrapper

data class WeatherDetailsState(
    val city: CityLocationDisplay? = null,
    val currentWeather: ResultWrapper<CurrentWeatherDetailsDisplay> = ResultWrapper.Loading,
    val forecastWeather: ResultWrapper<List<ForecastWeatherDisplay>> = ResultWrapper.Loading,
)