package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skycom.weatherapp.R
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CityLocationDisplay
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CurrentWeatherDetailsDisplay
import com.skycom.weatherapp.feature.weatherDetails.ui.model.ForecastWeatherDisplay
import com.skycom.weatherapp.feature.weatherDetails.ui.model.WeatherDetailsState

@Composable
fun WeatherDetailsScreen(
    city: CityLocation?,
) {
    city?.let {
        val viewmodel = hiltViewModel<WeatherDetailsViewModel>()
        val state = viewmodel.state.collectAsState().value
        viewmodel.loadWeatherDetails(it)
        WeatherDetailsScreenInternal(
            state = state,
        )
    }
}

@Composable
fun WeatherDetailsScreenInternal(
    state: WeatherDetailsState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
    ) {

        state.city?.let {
            CityData(it)
        }
        when (val currentWeather = state.currentWeather) {
            is ResultWrapper.Success -> CurrentWeatherSection(currentWeather.data)
            is ResultWrapper.Error   -> Text(text = stringResource(R.string.error_has_occurred))
            else                     -> CircularProgressIndicator()
        }
        when (val forecastWeather = state.forecastWeather) {
            is ResultWrapper.Success -> WeatherForecastSection(forecastWeather.data)
            is ResultWrapper.Error   -> Text(text = stringResource(R.string.error_has_occurred))
            else                     -> CircularProgressIndicator()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherDetailsPreview() {
    WeatherDetailsScreenInternal(
        state = WeatherDetailsState(
            city = CityLocationDisplay(
                place = "Springfield",
                coordinates = "39.7817° N, 89.6501° W"
            ),
            currentWeather = ResultWrapper.Success(
                CurrentWeatherDetailsDisplay(
                    description = "Clear Sky",
                    temperature = 22.5,
                    perceivedTemperature = 21.8,
                    windSpeed = 5.2,
                    humidity = 60,
                    pressure = 1013,
                    visibility = 10000,
                    cloudiness = 5,
                    snowIntensity = 0.0,
                    rainIntensity = 0.0,
                    sunrise = "06:45 AM",
                    sunset = "07:30 PM"
                )
            ),
            forecastWeather = ResultWrapper.Success(
                listOf(
                    ForecastWeatherDisplay(
                        date = "2025-02-08",
                        description = "Sunny",
                        icon = "sunny_icon",
                        perceivedTemperature = 23.0,
                        temperature = 24.5,
                        minTemperature = 18.5,
                        maxTemperature = 27.0,
                        pressure = 1012,
                        humidity = 55,
                        precipitation = 0,
                    ),
                    ForecastWeatherDisplay(
                        date = "2025-02-09",
                        description = "Partly Cloudy",
                        icon = "partly_cloudy_icon",
                        perceivedTemperature = 22.0,
                        temperature = 23.2,
                        minTemperature = 19.0,
                        maxTemperature = 26.5,
                        pressure = 1011,
                        humidity = 58,
                        precipitation = 10,
                    ),
                    ForecastWeatherDisplay(
                        date = "2025-02-10",
                        description = "Light Rain",
                        icon = "rainy_icon",
                        perceivedTemperature = 20.5,
                        temperature = 21.0,
                        minTemperature = 17.5,
                        maxTemperature = 24.0,
                        pressure = 1009,
                        humidity = 70,
                        precipitation = 40,
                    )
                )
            )
        )
    )
}