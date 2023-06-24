package com.izhxx.navcore.data.dao

import androidx.room.*
import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.data.model.LocationEntity.Companion.LOCATION_TABLE_NAME
import com.izhxx.navcore.utils.LocationsEntity
import io.reactivex.Single

@Dao
internal interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(locationsList: List<LocationEntity>)

    @Query("SELECT * FROM $LOCATION_TABLE_NAME")
    fun getLocations(): Single<LocationsEntity>

    @Query("SELECT * FROM $LOCATION_TABLE_NAME WHERE locationId LIKE :locationId")
    fun getLocationById(locationId: Int): Single<LocationEntity>

    //Optimize SQL query
    @Query("SELECT * FROM $LOCATION_TABLE_NAME WHERE startAxisX <= :x AND endAxisX >= :x AND startAxisY <= :y AND endAxisY <= :y")
    fun findLocationByOrdinates(x: Double, y: Double): Single<LocationEntity>

    @Query("SELECT * FROM $LOCATION_TABLE_NAME WHERE locationName LIKE :name")
    fun findLocationByName(name: String): Single<LocationsEntity>
}