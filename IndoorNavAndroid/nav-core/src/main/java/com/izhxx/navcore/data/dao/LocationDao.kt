package com.izhxx.navcore.data.dao

import androidx.room.*
import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.data.model.LocationEntity.Companion.LOCATION_TABLE_NAME
import io.reactivex.Single

@Dao
internal interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(locationsList: List<LocationEntity>)

    @Query("SELECT * FROM $LOCATION_TABLE_NAME")
    fun getLocations(): Single<List<LocationEntity>>
}