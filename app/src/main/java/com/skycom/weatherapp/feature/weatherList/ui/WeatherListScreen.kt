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
import androidx.compose.material3.Card
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
import com.skycom.weatherapp.core.common.components.WeatherIcon
import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.model.ResultWrapper
import com.skycom.weatherapp.core.common.util.annotateWithTemperatureColor
import com.skycom.weatherapp.feature.weatherList.ui.model.CityWeatherDisplay
import com.skycom.weatherapp.feature.weatherList.ui.model.WeatherListState

@Composable
fun WeatherListScreen(
    navigateToDetails: (CityLocation) -> Unit,
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
    navigateToDetails: (CityLocation) -> Unit,
    searchCity: (String) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = searchQuery,
            onValueChange = {
                searchQuery = it
                searchCity(it.text)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search for a city...") })
        Spacer(modifier = Modifier.height(16.dp))

        when (state.searchResults) {
            is ResultWrapper.Loading -> CircularProgressIndicator()
            is ResultWrapper.Success -> if (state.searchResults.data.isNotEmpty()) {
                SearchResultsList(
                    state.searchResults.data,
                    navigateToDetails = navigateToDetails,
                    saveCitySearch = saveCitySearch
                )
            }

            is ResultWrapper.Error   -> Text(
                text = state.searchResults.message ?: "Error loading recent searches"
            )

            is ResultWrapper.Idle    -> {}
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

            is ResultWrapper.Error   -> Text(
                text = state.recentSearches.message ?: "Error loading recent searches"
            )

            is ResultWrapper.Idle    -> {}
        }


    }
}


@Composable
fun RecentSearchesList(
    cities: List<CityWeatherDisplay>,
    navigateToDetails: (CityLocation) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    Text(
        text = "Recent Searches",
        style = MaterialTheme.typography.headlineMedium
    )
    LazyColumn {
        items(cities) { city ->
            CityWeatherItem(
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
    navigateToDetails: (CityLocation) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    Text(
        text = "Search Results",
        style = MaterialTheme.typography.headlineMedium
    )
    LazyColumn {
        items(cities) { city ->
            CityWeatherItem(
                city = city,
                navigateToDetails = navigateToDetails,
                saveCitySearch = saveCitySearch,
            )
        }
    }
}

@Composable
fun CityWeatherItem(
    city: CityWeatherDisplay,
    navigateToDetails: (CityLocation) -> Unit,
    saveCitySearch: (CityLocation) -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.padding(vertical = 4.dp)
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
                navigateToDetails(city.location)
            }
            .padding(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            WeatherIcon(
                modifier = Modifier.padding(
                        start = 8.dp,
                        end = 16.dp
                    ),
                iconCode = city.weatherIcon
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${city.location.name}, ${city.location.country}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = city.weatherDescription,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = city.temperature.annotateWithTemperatureColor(),
            )
        }
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
                        temperature = 25.0,
                        weatherDescription = "Sunny",
                        weatherIcon = "20d",
                    ),
                    CityWeatherDisplay(
                        CityLocation(
                            name = "Paris",
                            country = "France",
                            latitude = 0.0,
                            longitude = 0.0,
                        ),
                        temperature = 2.4,
                        weatherDescription = "Cloudy",
                        weatherIcon = "10d",
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
                        temperature = 12.0,
                        weatherDescription = "Rainy",
                        weatherIcon = "",
                    )
                )
            ),
        ),
        navigateToDetails = {},
        searchCity = {},
        saveCitySearch = {},
    )
}
