package com.skycom.weatherapp.feature.weatherList.ui.model

import com.skycom.weatherapp.core.common.model.ResultWrapper


data class WeatherListState(
    val searchResults: ResultWrapper<List<CityWeatherDisplay>> = ResultWrapper.Idle,
    val recentSearches: ResultWrapper<List<CityWeatherDisplay>> = ResultWrapper.Idle,
)