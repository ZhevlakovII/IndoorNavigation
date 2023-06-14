package com.izhxx.navclient.presentation.search

import androidx.lifecycle.MutableLiveData
import com.izhxx.navcore.domain.model.SearchHistory
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navshared.base.NavViewModel
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.domain.usecase.location.LocationUseCase
import com.izhxx.navclient.domain.usecase.search.SearchUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import com.izhxx.navclient.presentation.search.model.SearchHistoryPresentation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SearchViewModelImpl @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val searchUseCase: SearchUseCase,
    private val sharedDataUseCase: SharedDataUseCase,
) : NavViewModel(), SearchViewModel {

    private var locations: MutableList<Location> = mutableListOf()

    override val isLoading = MutableLiveData<Boolean>()
    override val error = MutableLiveData<ErrorModel>()

    override val openNavigationScreen = MutableLiveData<Boolean>()

    override val historyLocations = MutableLiveData<List<SearchHistoryPresentation>>()
    override val searchedLocations = MutableLiveData<List<Location>>()

    private var isSetDestination = false

    override fun onStart() {
        openNavigationScreen.value = false
        isSetDestination = sharedDataUseCase.getSharedData().isSetDestination

        getHistory()
        if (sharedDataUseCase.getSharedData().locationList.isNullOrEmpty()) {
            getLocations()
        } else {
            locations = sharedDataUseCase.getSharedData().locationList as MutableList<Location>
        }
    }

    override fun findLocation(input: String) {
        locationUseCase.findLocationByName(input)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                { searchedLocations.value = it },
                {
//                    error.value = ErrorModel(
//                        errorMessageResource = R.string.nav_ui_client_search_empty_locations,
//                        errorType = ErrorType.SCREEN_SHOWS
//                    )
                }
            )
            .untilCleared()
    }

    override fun selectItem(itemLocationId: Int) {
        locationUseCase
            .getLocationById(itemLocationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                {
                    if (isSetDestination) {
                        sharedDataUseCase.getSharedData().destination = it
                    } else {
                        sharedDataUseCase.getSharedData().currentUserPosition = it
                    }
                    openNavigationScreen.value = true
                },
                {
                    error.value = getError(it)
                }
            )
            .untilCleared()
    }

    private fun getHistory() {
        searchUseCase
            .getRequests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                { historyLocations.value = getPresentationHistory(it) },
                {
                    error.value = getError(it)
                }
            )
            .untilCleared()
    }

    private fun getLocations() {
        locationUseCase
            .getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                { locations = it as MutableList<Location> },
                {
                    error.value = getError(it)
                }
            )
            .untilCleared()
    }

    private fun getPresentationHistory(items: List<SearchHistory>): List<SearchHistoryPresentation> {
        val finalList = mutableListOf<SearchHistoryPresentation>()

        items.forEach { search ->
            val location = locations.find { it.locationId == search.locationId }

            location?.let {
                finalList.add(
                    SearchHistoryPresentation(
                        requestId = search.requestId,
                        locationId = location.locationId,
                        locationName = location.locationName
                    )
                )
            }
        }

        return if (finalList.isNotEmpty())
            finalList.sortedBy { it.requestId }.asReversed()
        else
            finalList
    }

    private fun getError(throwable: Throwable): ErrorModel = ErrorModel(
        errorMessage = throwable.localizedMessage ?: "Error without message", //Message to func
        errorType = ErrorType.TOAST_SHOWS
    )

}