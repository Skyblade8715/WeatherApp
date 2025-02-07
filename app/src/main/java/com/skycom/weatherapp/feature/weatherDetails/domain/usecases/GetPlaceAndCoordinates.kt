package com.skycom.weatherapp.feature.weatherDetails.domain.usecases

import com.skycom.weatherapp.core.common.model.CityLocation
import com.skycom.weatherapp.core.common.util.convertToDMS
import com.skycom.weatherapp.feature.weatherDetails.ui.model.CityLocationDisplay
import javax.inject.Inject

class GetPlaceAndCoordinates @Inject constructor() {
    operator fun invoke(cityLocation: CityLocation): CityLocationDisplay {
        return CityLocationDisplay(
            place = "${cityLocation.name}, ${cityLocation.country}",
            coordinates = "${cityLocation.latitude.convertToDMS(true)} " + cityLocation.longitude.convertToDMS(false)
        )

    }
}