package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemsRepo @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {
    fun getAllHistory() = searchHistoryDao.getAllHistory()

    fun getDatabaseLastId() = searchHistoryDao.getDatabaseLastId()

    fun insertSearchedLocation(searchLocation: SearchHistory) = searchHistoryDao.insertSearchedLocation(searchLocation)
}