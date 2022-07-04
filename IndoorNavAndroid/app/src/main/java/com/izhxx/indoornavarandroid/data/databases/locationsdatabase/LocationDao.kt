package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): Flow<List<Location>>

    @Query("SELECT * FROM locations WHERE locationId LIKE :id")
    suspend fun getLocationById(id: Int): Location

    @Query("SELECT * FROM locations WHERE name LIKE :name || '%'")
    suspend fun getLocationName(name: String): Location

    @Query("SELECT * FROM locations WHERE ru_name MATCH :name || '*'")
    suspend fun getRuLocationName(name: String): Location

    @Query("SELECT centerX FROM locations WHERE locationId LIKE :id")
    suspend fun getLocationCenterX(id: Int): Double
    @Query("SELECT centerY FROM locations WHERE locationId LIKE :id")
    suspend fun getLocationCenterY(id: Int): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(listOfLocations: List<Location>)

}