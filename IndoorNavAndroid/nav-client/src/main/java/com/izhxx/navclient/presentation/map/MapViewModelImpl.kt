package com.izhxx.navclient.presentation.map

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navshared.base.NavViewModel
import com.izhxx.navclient.R as NavClientR
import com.izhxx.navuikit.R as NavUiKitR
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.domain.usecase.location.LocationUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.onTap
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import javax.inject.Inject

internal class MapViewModelImpl @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val sharedDataUseCase: SharedDataUseCase,
    private val tilesProvider: TileStreamProvider
) : NavViewModel(), MapViewModel {

    override val mapState: MapState by mutableStateOf(
        MapState(
            levelCount = 5,
            fullWidth = 3546,
            fullHeight = 3580,
            tileSize = 256
        ).apply {
            addLayer(tilesProvider)
            onTap { x, y -> onMapTap(x, y) }
        }
    )
    private var locations: List<Location> = mutableListOf()

    override val isLoading = MutableLiveData<Boolean>()
    override val error = MutableLiveData<ErrorModel>()

    override val selectedLocation = MutableLiveData<Location>()

    override val openNavigationScreen = MutableLiveData<Boolean>()

    override fun onStart() {
        openNavigationScreen.value = false
        val locationsData = sharedDataUseCase.getSharedData().locationList
        if (locationsData.isNullOrEmpty()) {
            getLocations()
        } else {
            locations = locationsData
        }
    }

    override fun onButtonNavigateClick() {
        sharedDataUseCase.getSharedData().destination = selectedLocation.value
        openNavigationScreen.value = true
    }

    private fun onMapTap(x: Double, y: Double) {
        locationUseCase
            .findLocationByOrdinates(x, y)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe({
                it.coordinates.currentAxisX = x
                it.coordinates.currentAxisY = y

                selectedLocation.value = it
                showSelectedLocation()
            }, {
                error.value = ErrorModel(
                    errorMessage = it.localizedMessage
                        ?: "Error without message", //Change message to func name
                    errorType = ErrorType.TOAST_SHOWS
                )
            })
            .untilCleared()
    }

    private fun showSelectedLocation() {
        selectedLocation.value?.let {
            mapState.addMarker(
                id = it.locationId.toString(),
                x = it.coordinates.currentAxisX ?: it.coordinates.centerAxisX,
                y = it.coordinates.currentAxisY ?: it.coordinates.centerAxisY
            ) {
                Icon(
                    painter = painterResource(id = NavUiKitR.drawable.nav_uikit_ic_map_pin_24dp),
                    contentDescription = selectedLocation.value?.locationName,
                    modifier = Modifier.size(24.dp),
                    tint = Color(NavUiKitR.color.nav_uikit_interface_primary)
                )
            }
        } ?: run {
            error.value = ErrorModel(
                errorMessageResource = NavClientR.string.nav_client_map_show_location_error,
                errorType = ErrorType.SNACKBAR_SHOWS
            )
        }
    }

    private fun getLocations() {
        locationUseCase
            .getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                { locations = it },
                {
                    error.value = ErrorModel(
                        errorMessage = it.localizedMessage
                            ?: "Error without message", //Change message to func name
                        errorType = ErrorType.TOAST_SHOWS
                    )
                }
            )
            .untilCleared()
    }

}