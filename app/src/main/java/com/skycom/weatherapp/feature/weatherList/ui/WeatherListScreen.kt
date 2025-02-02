package com.skycom.weatherapp.feature.weatherList.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.feature.weatherList.domain.model.CityLocation
import com.skycom.weatherapp.feature.weatherList.ui.model.CityWeatherDisplay
import com.skycom.weatherapp.feature.weatherList.ui.model.WeatherListState
import com.skycom.weatherapp.feature.weatherList.ui.model.getTemperatureColor

@Composable
fun WeatherListScreen(
    navigateToDetails: (String) -> Unit
) {

    val viewModel: WeatherListViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    WeatherListScreenInternal(
        state = state,
        navigateToDetails = navigateToDetails,
        searchCity = viewModel::searchCity,
        saveCitySearch = viewModel::saveCitySearch,
    )

}

@Composable
fun WeatherListScreenInternal(
    state: WeatherListState,
    navigateToDetails: (String) -> Unit,
    searchCity: (String) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = searchQuery, onValueChange = {
            searchQuery = it
            searchCity(it.text)
        }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Search for a city...") })
        Spacer(modifier = Modifier.height(16.dp))

        when (state.searchResults) {
            is ResultWrapper.Loading -> CircularProgressIndicator()
            is ResultWrapper.Success ->
                if (state.searchResults.data.isNotEmpty()) {
                    SearchResultsList(
                        state.searchResults.data,
                        navigateToDetails = navigateToDetails,
                        saveCitySearch = saveCitySearch
                    )
                }

            is ResultWrapper.Error -> Text(
                text = state.searchResults.message ?: "Error loading recent searches"
            )

            is ResultWrapper.Idle -> {}
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state.recentSearches) {
            is ResultWrapper.Loading -> CircularProgressIndicator()
            is ResultWrapper.Success -> {
                if (state.recentSearches.data.isNotEmpty()) {
                    RecentSearchesList(
                        cities = state.recentSearches.data,
                        navigateToDetails = navigateToDetails,
                        saveCitySearch = saveCitySearch,
                    )
                }
            }

            is ResultWrapper.Error -> Text(
                text = state.recentSearches.message ?: "Error loading recent searches"
            )

            is ResultWrapper.Idle -> {}
        }


    }
}


@Composable
fun RecentSearchesList(
    cities: List<CityWeatherDisplay>,
    navigateToDetails: (String) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    Text(text = "Recent Searches", style = MaterialTheme.typography.headlineMedium)
    LazyColumn {
        items(cities) { city ->
            CityRow(
                city = city,
                navigateToDetails = navigateToDetails,
                saveCitySearch = saveCitySearch,
            )
        }
    }
}

@Composable
fun SearchResultsList(
    cities: List<CityWeatherDisplay>,
    navigateToDetails: (String) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    Text(text = "Search Results", style = MaterialTheme.typography.headlineMedium)
    LazyColumn {
        items(cities) { city ->
            CityRow(
                city = city,
                navigateToDetails = navigateToDetails,
                saveCitySearch = saveCitySearch,
            )
        }
    }
}

@Composable
fun CityRow(
    city: CityWeatherDisplay,
    navigateToDetails: (String) -> Unit,
    saveCitySearch: (CityLocation) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            saveCitySearch(
                CityLocation(
                    city.location.name,
                    city.location.country,
                    city.location.latitude,
                    city.location.longitude,
                )
            )
            navigateToDetails(city.location.name)
        }
        .padding(8.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${city.location.name}, ${city.location.country}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = city.weatherDescription, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = city.temperatureDisplay,
            color = city.temperatureColor,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherListScreenPreview() {
    WeatherListScreenInternal(
        state = WeatherListState(
            recentSearches = ResultWrapper.Success(
                listOf(
                    CityWeatherDisplay(
                        CityLocation(
                            name = "London",
                            country = "UK",
                            latitude = 0.0,
                            longitude = 0.0,
                        ),
                        temperatureDisplay = "10°C",
                        weatherDescription = "Sunny",
                        weatherIcon = "",
                        temperatureColor = getTemperatureColor(10),
                    ),
                    CityWeatherDisplay(
                        CityLocation(
                            name = "Paris",
                            country = "France",
                            latitude = 0.0,
                            longitude = 0.0,
                        ),
                        temperatureDisplay = "14°C",
                        weatherDescription = "Cloudy",
                        weatherIcon = "",
                        temperatureColor = getTemperatureColor(14),
                    )
                ),
            ),
            searchResults = ResultWrapper.Success(
                listOf(
                    CityWeatherDisplay(
                        CityLocation(
                            name = "New York",
                            country = "USA",
                            latitude = 0.0,
                            longitude = 0.0,
                        ),
                        temperatureDisplay = "12°C",
                        weatherDescription = "Rainy",
                        weatherIcon = "",
                        temperatureColor = getTemperatureColor(12),
                    )
                )
            ),
        ),
        navigateToDetails = {},
        searchCity = {},
        saveCitySearch = {},
    )
}
