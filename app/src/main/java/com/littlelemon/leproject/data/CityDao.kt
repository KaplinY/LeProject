package com.littlelemon.leproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city: City)

    @Query("SELECT * FROM city_table WHERE city = :name")
    fun findCityByName(name: String): City

    @Query("SELECT * FROM city_table")
    fun getAllCities(): List<City>

    @Update
    suspend fun updateCityDetails(city: City)

    @Delete
    suspend fun deleteCity(city: City)
}