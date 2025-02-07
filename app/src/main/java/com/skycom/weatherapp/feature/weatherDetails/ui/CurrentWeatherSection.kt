package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skycom.weatherapp.R
import com.skycom.weatherapp.core.common.util.annotateWithTemperatureColor
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CurrentWeatherDetailsDisplay


@Composable
fun CurrentWeatherSection(
    data: CurrentWeatherDetailsDisplay,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                text = data.description,
            )

            Spacer(modifier = Modifier.height(4.dp))

            IconWithText(
                resId = R.drawable.ic_perceived_temp,
                annotatedText = buildAnnotatedString {
                    append(stringResource(R.string.temperature))
                    append(" ")
                    append(data.temperature.annotateWithTemperatureColor())
                },
                contentDescription = "Temperature Icon"
            )

            IconWithText(
                resId = R.drawable.ic_temperature,
                annotatedText = buildAnnotatedString {
                    append(stringResource(R.string.perceived_temperature))
                    append(" ")
                    append(data.perceivedTemperature.annotateWithTemperatureColor())
                },
                contentDescription = "Perceived Temperature Icon"
            )

            IconWithText(
                resId = R.drawable.ic_wind,
                text = stringResource(
                    R.string.wind_speed_m_s,
                    data.windSpeed
                ),
                contentDescription = "Wind Speed Icon"
            )

            IconWithText(
                resId = R.drawable.ic_humidity,
                text = stringResource(
                    R.string.humidity,
                    data.humidity
                ),
                contentDescription = "Humidity Icon"
            )

            IconWithText(
                resId = R.drawable.ic_pressure,
                text = stringResource(
                    R.string.pressure_hpa,
                    data.pressure
                ),
                contentDescription = "Pressure Icon"
            )

            IconWithText(
                resId = R.drawable.ic_visibility,
                text = stringResource(
                    R.string.visibility_m,
                    data.visibility
                ),
                contentDescription = "Visibility Icon"
            )

            IconWithText(
                resId = R.drawable.ic_cloudiness,
                text = "Cloudiness: ${data.cloudiness}%",
                contentDescription = "Clouds Icon"
            )

            IconWithText(
                resId = R.drawable.ic_snow,
                text = stringResource(
                    R.string.snow_intensity_mm_h,
                    data.snowIntensity
                ),
                contentDescription = "Snow Icon"
            )

            IconWithText(
                resId = R.drawable.ic_rain,
                text = stringResource(
                    R.string.rain_intensity_mm_h,
                    data.rainIntensity
                ),
                contentDescription = "Rain Icon"
            )

            SunsetData(
                sunrise = data.sunrise,
                sunset = data.sunset
            )
        }
    }
}


@Composable
fun SunsetData(
    sunrise: String,
    sunset: String,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(
            modifier.weight(1f)
        )
        Text(
            fontWeight = FontWeight.Bold,
            text = sunrise
        )
        Icon(
            tint = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Outlined.WbSunny,
            contentDescription = "Sunrise Icon",
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(0.2f),
            color = Color.Gray,
            thickness = 1.dp
        )
        Icon(
            tint = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Outlined.DarkMode,
            contentDescription = "Sunset Icon"
        )
        Text(
            fontWeight = FontWeight.Bold,
            text = sunset
        )
        Spacer(
            modifier.weight(1f)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherSectionPreview() {
    CurrentWeatherSection(
        data = CurrentWeatherDetailsDisplay(
            description = "Sunny",
            temperature = 25.0,
            perceivedTemperature = 2.0,
            windSpeed = 5.0,
            humidity = 60,
            pressure = 1013,
            visibility = 10000,
            cloudiness = 23,
            snowIntensity = 10.0,
            rainIntensity = 0.0,
            sunrise = "06:00",
            sunset = "18:00",
        )
    )
}
