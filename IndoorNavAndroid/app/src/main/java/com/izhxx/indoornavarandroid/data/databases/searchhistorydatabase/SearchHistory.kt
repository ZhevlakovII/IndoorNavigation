package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
@Fts4(contentEntity = SearchHistory::class)
data class SearchHistory(
    @PrimaryKey
    val searchId: Int,

    @ColumnInfo(name = "name")
    val searchHistoryName: String,

    @ColumnInfo(name = "ru_name")
    val searchHistoryRuName: String,

    val searchedLocationId: Int
)