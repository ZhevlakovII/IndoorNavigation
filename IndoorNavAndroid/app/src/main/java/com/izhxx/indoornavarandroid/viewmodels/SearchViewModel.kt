package com.izhxx.indoornavarandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.LocationRepo
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val searchHistoryRepo: SearchHistoryRepo,
    private val locationsRepo: LocationRepo
): ViewModel() {
    val historyList = searchHistoryRepo.getAllHistory().asLiveData()

    lateinit var searchedLocation: LiveData<List<Location>>

    fun searchLocation(locationName: String) {
        searchedLocation = locationsRepo.getLocationByName(locationName).asLiveData()
    }
}