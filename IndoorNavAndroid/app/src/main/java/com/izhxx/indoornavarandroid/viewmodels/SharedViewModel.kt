package com.izhxx.indoornavarandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.LocationRepo
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistory
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject internal constructor(
    private val locationsRepo: LocationRepo,
    private val searchHistoryRepo: SearchHistoryRepo
): ViewModel() {
    val cardState = MutableLiveData<Boolean>().apply { value = false }

    var pickedLocation = MutableLiveData<Location>().apply { value = null }

    fun changeCardState(state: Boolean) {
        cardState.value = state
    }

    fun changePickedLocation(location: Location) {
        pickedLocation.value = location
    }

    fun changePickedLocationWithId(searchedLocationId: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            val result = locationsRepo.getLocationById(searchedLocationId)
            pickedLocation.value = result
        }
    }

    fun insertHistoryItem(item: SearchHistory) {
        viewModelScope.launch (Dispatchers.IO) {
            searchHistoryRepo.deleteSearchedLocation(item)
            searchHistoryRepo.insertSearchedLocation(
                SearchHistory(
                    searchHistoryRepo.getDatabaseLastId(),
                    item.searchHistoryName,
                    item.searchedLocationId
                )
            )
        }
    }

    fun insertNewItem(item: Location) {
        viewModelScope.launch (Dispatchers.IO) {
            searchHistoryRepo.insertSearchedLocation(
                SearchHistory(
                    searchHistoryRepo.getDatabaseLastId(),
                    item.locationName!!,
                    item.locationId
                )
            )
        }
    }
}