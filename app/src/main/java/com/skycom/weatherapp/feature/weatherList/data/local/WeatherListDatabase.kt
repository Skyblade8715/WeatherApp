package com.skycom.weatherapp.feature.weatherList.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchedCityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherListDatabase : RoomDatabase() {
    abstract fun weatherListDao(): WeatherListDao
}
