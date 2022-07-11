package com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryRepo @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {
    fun getAllHistory() = searchHistoryDao.getAllHistory()

    fun getDatabaseLastId() = searchHistoryDao.getDatabaseLastId()

    fun insertSearchedLocation(searchLocation: SearchHistory) = searchHistoryDao.insertSearchedLocation(searchLocation)

    fun deleteSearchedLocation(searchLocation: SearchHistory) = searchHistoryDao.deleteSearchedLocation(searchLocation)
}