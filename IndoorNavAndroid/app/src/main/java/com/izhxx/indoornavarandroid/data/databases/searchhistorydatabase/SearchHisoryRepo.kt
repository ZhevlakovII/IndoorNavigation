package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemsRepo @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {
    suspend fun getAllHistory() = searchHistoryDao.getAllHistory()

    suspend fun getDatabaseLastId() = searchHistoryDao.getDatabaseLastId()

    suspend fun insertSearchedLocation(searchLocation: SearchHistory) = searchHistoryDao.insertSearchedLocation(searchLocation)
}