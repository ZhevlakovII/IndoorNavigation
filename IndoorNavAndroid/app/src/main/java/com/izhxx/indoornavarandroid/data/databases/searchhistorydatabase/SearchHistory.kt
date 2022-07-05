package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import androidx.room.*

@Entity(tableName = "search_history")
@Fts4(notIndexed = ["id"], tokenizer = FtsOptions.TOKENIZER_UNICODE61)
data class SearchHistory(
    @ColumnInfo(name = "id")
    val searchId: Int,

    @ColumnInfo(name = "name")
    val searchHistoryName: String,

    @ColumnInfo(name = "ru_name")
    val searchHistoryRuName: String,

    val searchedLocationId: Int
)