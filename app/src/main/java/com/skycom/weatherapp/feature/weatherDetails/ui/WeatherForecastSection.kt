package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skycom.weatherapp.R
import com.skycom.weatherapp.core.common.components.WeatherIcon
import com.skycom.weatherapp.core.common.util.annotateWithTemperatureColor
import com.skycom.weatherapp.feature.weatherDetails.ui.model.ForecastWeatherDisplay


@Composable
fun WeatherForecastSection(data: List<ForecastWeatherDisplay>) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            text = "Forecast",
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            data.forEach {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                ForecastItem(data = it)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ForecastItem(data: ForecastWeatherDisplay) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = data.date,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.description,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    textAlign = TextAlign.Start,
                    minLines = 2, //Most descriptions are short but there are some longer ones which need 2 lines
                )
                Spacer(modifier = Modifier.height(4.dp))
                WeatherIcon(data.icon)
                Spacer(modifier = Modifier.height(4.dp))
            }

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Temperature: ${data.temperature.annotateWithTemperatureColor()}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Perceived: ${data.perceivedTemperature.annotateWithTemperatureColor()}",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(
                        R.string.min_max_temp,
                        data.minTemperature,
                        data.maxTemperature
                    ),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(
                        R.string.precipitation,
                        data.precipitation
                    ),
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = R.drawable.ic_pressure),
                contentDescription = "Pressure Icon",
            )
            Text(
                text = "Pressure: ${data.pressure} hPa",
            )
            Spacer(
                modifier = Modifier.weight(1f),
            )
            Icon(
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = R.drawable.ic_humidity),
                contentDescription = "Humidity Icon",
            )
            Text(
                text = "Humidity: ${data.humidity}%",
            )
        }
        Spacer(
            modifier = Modifier.height(8.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherForecastSectionPreview() {
    WeatherForecastSection(
        data = listOf(
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
}