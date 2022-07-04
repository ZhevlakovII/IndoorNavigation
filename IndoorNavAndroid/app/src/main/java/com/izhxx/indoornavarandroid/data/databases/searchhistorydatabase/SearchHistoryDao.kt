package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY searchedLocationId DESC")
    suspend fun getAllHistory(): Flow<List<SearchHistory>>

    @Query("SELECT * FROM search_history ORDER BY searchedLocationId DESC LIMIT 1")
    suspend fun getDatabaseLastId(): Int

    @Insert
    suspend fun insertSearchedLocation(searchLocation: SearchHistory)
}