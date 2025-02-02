package com.skycom.weatherapp.feature.weatherList.di

import android.content.Context
import androidx.room.Room
import com.skycom.weatherapp.core.network.OpenWeatherApi
import com.skycom.weatherapp.feature.weatherList.data.local.DefaultWeatherListLocalDataSource
import com.skycom.weatherapp.feature.weatherList.data.local.WeatherListDao
import com.skycom.weatherapp.feature.weatherList.data.local.WeatherListDatabase
import com.skycom.weatherapp.feature.weatherList.data.remote.DefaultWeatherListRemoteDataSource
import com.skycom.weatherapp.feature.weatherList.data.repository.DefaultWeatherListRepository
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListLocalDataSource
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRemoteDataSource
import com.skycom.weatherapp.feature.weatherList.domain.repository.WeatherListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherListModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherListDatabase {
        return Room.databaseBuilder(
            context,
            WeatherListDatabase::class.java,
            "weather_list_db"
        ).build()
    }

    @Provides
    fun provideWeatherListDao(database: WeatherListDatabase): WeatherListDao {
        return database.weatherListDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: WeatherListDao): WeatherListLocalDataSource {
        return DefaultWeatherListLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: OpenWeatherApi): WeatherListRemoteDataSource {
        return DefaultWeatherListRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideWeatherListRepository(
        remoteDataSource: WeatherListRemoteDataSource,
        localDataSource: WeatherListLocalDataSource
    ): WeatherListRepository {
        return DefaultWeatherListRepository(remoteDataSource, localDataSource)
    }
}
