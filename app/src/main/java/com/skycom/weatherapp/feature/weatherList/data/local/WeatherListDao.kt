package com.skycom.weatherapp.feature.weatherList.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeatherListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: SearchedCityEntity)

    @Query("SELECT * FROM searched_cities ORDER BY name ASC")
    suspend fun getSavedCities(): List<SearchedCityEntity>
}
