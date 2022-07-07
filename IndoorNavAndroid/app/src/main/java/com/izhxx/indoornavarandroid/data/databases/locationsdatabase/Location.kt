package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import androidx.room.*

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val locationId: Int,

    @ColumnInfo (name = "name")
    val locationName: String?,

    val startX: Double,
    val startY: Double,

    val endX: Double,
    val endY: Double,

    val centerX: Double,
    val centerY: Double
)