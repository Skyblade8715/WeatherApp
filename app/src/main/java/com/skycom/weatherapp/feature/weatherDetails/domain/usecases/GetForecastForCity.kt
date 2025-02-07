package com.skycom.weatherapp.feature.weatherDetails.domain.usecases

import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.util.formatTimestampToDateAndTime
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsRepository
import com.skycom.weatherapp.feature.weatherDetails.ui.model.ForecastWeatherDisplay
import javax.inject.Inject

class GetForecastForCity @Inject constructor(
    private val repository: WeatherDetailsRepository,
) {
    suspend operator fun invoke(cityLocation: CityLocation): ResultWrapper<List<ForecastWeatherDisplay>> {
        val forecastWeather = repository.getForecastWeatherForCity(cityLocation)
        if (forecastWeather is ResultWrapper.Success) {
            return ResultWrapper.Success(forecastWeather.data.map { forecastItem ->
                ForecastWeatherDisplay(
                    description = forecastItem.weather.description,
                    temperature = forecastItem.weather.temperature,
                    perceivedTemperature = forecastItem.weather.feelsLikeTemperature,
                    minTemperature = forecastItem.minTemp,
                    maxTemperature = forecastItem.maxTemp,
                    humidity = forecastItem.weather.humidity,
                    pressure = forecastItem.weather.pressure,
                    date = formatTimestampToDateAndTime(forecastItem.dateTime),
                    precipitation = (forecastItem.precipitationProbability * 100.0).toInt(),
                    icon = forecastItem.icon,
                )
            })
        }
        return ResultWrapper.Error()
    }

}