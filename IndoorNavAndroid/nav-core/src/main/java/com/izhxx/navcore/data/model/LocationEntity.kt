package com.izhxx.navcore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.izhxx.navcore.data.model.LocationEntity.Companion.LOCATION_TABLE_NAME

@Entity(tableName = LOCATION_TABLE_NAME)
internal data class LocationEntity(
    @PrimaryKey
    val locationId: Int,
    val locationName: String,
    val startAxisX: Double,
    val centerAxisX: Double,
    val endAxisX: Double,
    val startAxisY: Double,
    val centerAxisY: Double,
    val endAxisY: Double,
) {
    companion object {
        internal const val LOCATION_TABLE_NAME = "location_table"
    }

}