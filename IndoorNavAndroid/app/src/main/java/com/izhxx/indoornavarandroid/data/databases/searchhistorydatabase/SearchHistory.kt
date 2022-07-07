package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import androidx.room.*

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val searchId: Int,

    @ColumnInfo(name = "name")
    val searchHistoryName: String,

    val searchedLocationId: Int
)