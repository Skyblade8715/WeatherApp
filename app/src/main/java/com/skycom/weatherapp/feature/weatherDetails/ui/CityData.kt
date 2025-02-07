package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CityLocationDisplay

@Composable
fun CityData(cityLocation: CityLocationDisplay) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            text = cityLocation.place,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    bottom = 16.dp
                ),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            textAlign = TextAlign.Center,
            text = cityLocation.coordinates,
        )
    }
}
