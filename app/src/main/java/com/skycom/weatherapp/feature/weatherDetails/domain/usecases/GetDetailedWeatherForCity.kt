package com.skycom.weatherapp.feature.weatherDetails.domain.usecases

import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.util.formatTimestampToMilitaryTime
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsRepository
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CurrentWeatherDetailsDisplay
import javax.inject.Inject

class GetDetailedWeatherForCity @Inject constructor(
    private val repository: WeatherDetailsRepository,
) {
    suspend operator fun invoke(cityLocation: CityLocation): ResultWrapper<CurrentWeatherDetailsDisplay> {
        val currentWeather = repository.getCurrentWeatherForCity(cityLocation)
        if (currentWeather is ResultWrapper.Success) {
            return ResultWrapper.Success(
                CurrentWeatherDetailsDisplay(
                    description = currentWeather.data.weather.description,
                    temperature = currentWeather.data.weather.temperature,
                    perceivedTemperature = currentWeather.data.weather.feelsLikeTemperature,
                    windSpeed = currentWeather.data.windSpeed,
                    humidity = currentWeather.data.weather.humidity,
                    pressure = currentWeather.data.weather.pressure,
                    visibility = currentWeather.data.visibility,
                    cloudiness = currentWeather.data.cloudsPercentage.toInt(),
                    snowIntensity = currentWeather.data.snowIntensity1h,
                    rainIntensity = currentWeather.data.rainIntensity1h,
                    sunrise = formatTimestampToMilitaryTime(currentWeather.data.sunrise),
                    sunset = formatTimestampToMilitaryTime(currentWeather.data.sunset),
                )
            )
        }
        return ResultWrapper.Error()
    }

}