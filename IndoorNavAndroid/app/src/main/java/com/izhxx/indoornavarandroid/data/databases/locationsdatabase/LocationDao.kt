package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<Location>>

    @Query("SELECT * FROM locations WHERE id LIKE :id")
    fun getLocationById(id: Int): Location

    @Query("SELECT * FROM locations WHERE name LIKE '%' || :name || '%'")
    fun getLocationByName(name: String): Flow<List<Location>>

    @Query("SELECT centerX FROM locations WHERE id LIKE :id")
    fun getLocationCenterX(id: Int): Double
    @Query("SELECT centerY FROM locations WHERE id LIKE :id")
    fun getLocationCenterY(id: Int): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(listOfLocations: List<Location>)

}