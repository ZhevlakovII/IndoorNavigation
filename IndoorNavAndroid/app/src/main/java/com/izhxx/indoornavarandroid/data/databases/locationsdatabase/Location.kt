package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import androidx.room.*

@Entity(tableName = "locations")
@Fts4(notIndexed = ["id"], tokenizer = FtsOptions.TOKENIZER_UNICODE61)
data class Location(
    @ColumnInfo(name = "id")
    val locationId: Int,

    @ColumnInfo (name = "name")
    val locationName: String?,

    @ColumnInfo (name = "ru_name")
    val locationRuName: String?,

    val startX: Double,
    val startY: Double,

    val endX: Double,
    val endY: Double,

    val centerX: Double,
    val centerY: Double
)