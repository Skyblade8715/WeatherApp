package com.skycom.weatherapp.feature.weatherDetails.data

import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.network.model.RawCurrentWeatherResponse
import com.skycom.weatherapp.core.network.model.RawWeatherForecastResponse
import com.skycom.weatherapp.feature.weatherDetails.domain.model.CurrentWeather
import com.skycom.weatherapp.feature.weatherDetails.domain.model.ForecastWeather
import com.skycom.weatherapp.feature.weatherDetails.domain.model.Weather
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsDataSource
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsRepository
import javax.inject.Inject

class DefaultWeatherDetailsRepository @Inject constructor(
    private val dataSource: WeatherDetailsDataSource,
) : WeatherDetailsRepository {
    override suspend fun getCurrentWeatherForCity(city: CityLocation): ResultWrapper<CurrentWeather> {
        val result = dataSource.getCurrentWeather(
            lat = city.latitude,
            lon = city.longitude,
        )
        return if (result is ResultWrapper.Success) {
            ResultWrapper.Success(
                result.data.toWeather()
            )
        } else {
            result as ResultWrapper.Error
        }
    }

    override suspend fun getForecastWeatherForCity(city: CityLocation): ResultWrapper<List<ForecastWeather>> {
        val result = dataSource.getForecastWeather(
            lat = city.latitude,
            lon = city.longitude,
        )
        return if (result is ResultWrapper.Success) {
            ResultWrapper.Success(
                result.data.toForecastWeather()
            )
        } else {
            result as ResultWrapper.Error
        }
    }

    private fun RawCurrentWeatherResponse.toWeather() = CurrentWeather(

        rainIntensity1h = this.rain?.rainIntensity ?: 0.0,
        snowIntensity1h = this.snow?.snowIntensity ?: 0.0,
        visibility = this.visibility,
        windSpeed = this.wind.windSpeed,
        cloudsPercentage = this.clouds.cloudsPercentage,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset,
        weather = Weather(
            description = this.weather.firstOrNull()?.description ?: "",
            temperature = this.main.temp,
            feelsLikeTemperature = this.main.feelsLike,
            pressure = this.main.pressure,
            humidity = this.main.humidity,
        )
    )

    private fun RawWeatherForecastResponse.toForecastWeather(): List<ForecastWeather> {
        return this.list.map {
            ForecastWeather(
                precipitationProbability = it.precipitationProbability,
                icon = it.weather.firstOrNull()?.icon ?: "",
                dateTime = it.dateTime,
                minTemp = it.main.tempMin,
                maxTemp = it.main.tempMax,
                weather = Weather(
                    description = it.weather.firstOrNull()?.description ?: "",
                    temperature = it.main.temp,
                    feelsLikeTemperature = it.main.feelsLike,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity,
                )
            )
        }
    }
}
