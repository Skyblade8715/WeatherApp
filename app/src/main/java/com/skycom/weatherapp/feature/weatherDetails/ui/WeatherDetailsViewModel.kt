package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherDetails.domain.usecases.GetDetailedWeatherForCity
import com.skycom.weatherapp.feature.weatherDetails.domain.usecases.GetForecastForCity
import com.skycom.weatherapp.feature.weatherDetails.domain.usecases.GetPlaceAndCoordinates
import com.skycom.weatherapp.feature.weatherDetails.ui.model.WeatherDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getDetailedWeatherForCity: GetDetailedWeatherForCity,
    private val getForecastForCity: GetForecastForCity,
    private val getPlaceAndCoordinates: GetPlaceAndCoordinates,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherDetailsState())
    val state: StateFlow<WeatherDetailsState> = _state

    fun loadWeatherDetails(city: CityLocation) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                city = getPlaceAndCoordinates(city),
                currentWeather = getDetailedWeatherForCity(city),
                forecastWeather = getForecastForCity(city),
            )
        }
    }
}