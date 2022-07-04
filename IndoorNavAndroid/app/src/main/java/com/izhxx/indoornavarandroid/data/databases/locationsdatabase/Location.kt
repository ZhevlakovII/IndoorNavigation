package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
@Fts4(contentEntity = Location::class)
data class Location(
    @PrimaryKey
    val locationId: Int,

    @ColumnInfo (name = "name")
    val locationName: String,

    @ColumnInfo (name = "ru_name")
    val locationRuName: String,

    val startX: Double,
    val startY: Double,

    val endX: Double,
    val endY: Double,

    val centerX: Double,
    val centerY: Double
)