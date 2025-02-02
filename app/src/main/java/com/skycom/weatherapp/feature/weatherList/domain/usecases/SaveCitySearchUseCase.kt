package com.skycom.weatherapp.feature.weatherList.domain.usecases

import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRepository
import javax.inject.Inject

class SaveCitySearchUseCase @Inject constructor(
    private val repository: WeatherListRepository
) {
    suspend operator fun invoke(cityLocation: CityLocation) {
        repository.saveCitySearch(cityLocation)
    }
}