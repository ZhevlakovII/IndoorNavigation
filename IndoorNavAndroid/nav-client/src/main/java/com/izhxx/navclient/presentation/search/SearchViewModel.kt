package com.izhxx.navclient.presentation.search

import androidx.lifecycle.LiveData
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.presentation.search.model.SearchHistoryPresentation

internal interface SearchViewModel {

    val isLoading: LiveData<Boolean>
    val error: LiveData<ErrorModel>

    val openNavigationScreen: LiveData<Boolean>

    val historyLocations: LiveData<List<SearchHistoryPresentation>>
    val searchedLocations: LiveData<List<Location>>

    fun onStart()

    fun findLocation(input: String)

    fun selectItem(itemLocationId: Int)
}