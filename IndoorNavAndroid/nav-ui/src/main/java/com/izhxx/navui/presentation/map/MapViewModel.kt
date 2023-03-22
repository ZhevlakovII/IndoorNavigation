package com.izhxx.navui.presentation.map

import androidx.lifecycle.LiveData
import com.izhxx.navcore.domain.model.location.Location
import ovh.plrapps.mapcompose.ui.state.MapState

internal interface MapViewModel {

    val mapState: MapState
    val isLoading: LiveData<Boolean>
    val selectedLocation: LiveData<Location>
    val error: LiveData<String>

    fun onStart()
}