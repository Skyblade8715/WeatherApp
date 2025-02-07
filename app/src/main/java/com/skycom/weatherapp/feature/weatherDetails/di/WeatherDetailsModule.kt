package com.skycom.weatherapp.feature.weatherDetails.di

import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.feature.weatherDetails.data.DefaultWeatherDetailsDataSource
import com.skycom.weatherapp.feature.weatherDetails.data.DefaultWeatherDetailsRepository
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsDataSource
import com.skycom.weatherapp.feature.weatherDetails.domain.repository.WeatherDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherDetailsModule {
    @Provides
    @Singleton
    fun provideWeatherDetailsDataSource(api: OpenWeatherApi): WeatherDetailsDataSource {
        return DefaultWeatherDetailsDataSource(api)
    }

    @Provides
    @Singleton
    fun provideWeatherDetailsRepository(
        dataSource: WeatherDetailsDataSource,
    ): WeatherDetailsRepository {
        return DefaultWeatherDetailsRepository(dataSource)
    }
}