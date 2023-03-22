package com.izhxx.navui.presentation.map

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
import com.izhxx.navuikit.R
import com.izhxx.navui.domain.usecase.location.LocationUseCase
import com.izhxx.navui.presentation.base.BaseViewModel
import com.izhxx.navui.presentation.map.utils.findLocation
import com.izhxx.navui.presentation.map.utils.mapProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.onTap
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import javax.inject.Inject

internal class MapViewModelImpl @Inject constructor(
    private val locationUseCase: LocationUseCase
) : BaseViewModel(), MapViewModel {

    private val mapTiles: TileStreamProvider
        get() = mapProvider()
    private var locations: MutableList<Location> = mutableListOf()

    override val isLoading = MutableLiveData<Boolean>()
    override val error = MutableLiveData<String>()
    override val selectedLocation = MutableLiveData<Location>()
    override val mapState: MapState by mutableStateOf(
        MapState(
            levelCount = 5,
            fullWidth = 3546,
            fullHeight = 3580,
            tileSize = 256
        ).apply {
            addLayer(mapTiles)
            onTap { x, y -> onMapTap(x, y) }
        }
    )

    override fun onStart() {
        getLocations()
    }

    private fun onMapTap(x: Double, y: Double) {
        findLocation(x, y, locations)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe({
                selectedLocation.value = it
                showSelectedLocation()
            }, {
                error.value = it.localizedMessage
            })
            .untilCleared()
    }

    private fun showSelectedLocation() {
        if (selectedLocation.value?.coordinates?.currentAxisX != null &&
            selectedLocation.value?.coordinates?.currentAxisY != null
        ) {
            mapState.addMarker(
                selectedLocation.value?.locationId.toString(),
                selectedLocation.value?.coordinates?.currentAxisX!!,
                selectedLocation.value?.coordinates?.currentAxisY!!
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.nav_ui_kit_ic_map_pin_24dp),
                    contentDescription = selectedLocation.value?.locationName,
                    modifier = Modifier.size(24.dp),
                    tint = Color(R.color.nav_ui_kit_interface_primary)
                )
            }
        } else {
            mapState.addMarker(
                selectedLocation.value?.locationId.toString(),
                selectedLocation.value?.coordinates?.centerAxisX!!,
                selectedLocation.value?.coordinates?.centerAxisY!!
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.nav_ui_kit_ic_map_pin_24dp),
                    contentDescription = selectedLocation.value?.locationName,
                    modifier = Modifier.size(24.dp),
                    tint = Color(R.color.nav_ui_kit_interface_primary)
                )
            }
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
                { locations = it as MutableList<Location> },
                { error.value = it.localizedMessage }
            )
            .untilCleared()
    }

}