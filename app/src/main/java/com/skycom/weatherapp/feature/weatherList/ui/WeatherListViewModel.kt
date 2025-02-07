package com.skycom.weatherapp.feature.weatherList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.usecases.GetRecentSearchesUseCase
import com.skycom.weatherapp.feature.weatherList.domain.usecases.SaveCitySearchUseCase
import com.skycom.weatherapp.feature.weatherList.domain.usecases.SearchCitiesWithWeatherUseCase
import com.skycom.weatherapp.feature.weatherList.ui.model.WeatherListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase,
    private val searchCitiesWithWeatherUseCase: SearchCitiesWithWeatherUseCase,
    private val saveCitySearchUseCase: SaveCitySearchUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(WeatherListState())
    val state: StateFlow<WeatherListState> = _state

    init {
        loadRecentSearches()
    }

    fun searchCity(cityName: String) {
        if(cityName.isBlank()) return
        viewModelScope.launch {
            _state.value = _state.value.copy(searchResults = ResultWrapper.Loading)
            val result = searchCitiesWithWeatherUseCase(cityName)
            _state.value = _state.value.copy(searchResults = result)
        }
    }

    private fun loadRecentSearches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(recentSearches = ResultWrapper.Loading)
            val result = getRecentSearchesUseCase()
            _state.value = _state.value.copy(recentSearches = result)
        }
    }

    fun saveCitySearch(city: CityLocation) {
        viewModelScope.launch {
            saveCitySearchUseCase(city)
        }
    }
}
