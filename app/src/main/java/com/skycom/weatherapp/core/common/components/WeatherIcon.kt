package com.skycom.weatherapp.core.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skycom.weatherapp.R


@Composable
fun WeatherIcon(
    iconCode: String,
    modifier: Modifier = Modifier,
) {
    val iconUrl = stringResource(
        R.string.weather_icon_link,
        iconCode
    )

    AsyncImage(
        model = iconUrl,
        contentDescription = "Weather icon",
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
