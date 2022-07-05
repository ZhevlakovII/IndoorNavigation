package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAllHistory(): Flow<List<SearchHistory>>

    @Query("SELECT * FROM search_history ORDER BY id DESC LIMIT 1")
    fun getDatabaseLastId(): Int

    @Insert
    fun insertSearchedLocation(searchLocation: SearchHistory)
}