package com.izhxx.navclient.presentation.map

import androidx.lifecycle.LiveData
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navclient.domain.model.error.ErrorModel
import ovh.plrapps.mapcompose.ui.state.MapState

internal interface MapViewModel {

    val mapState: MapState

    val isLoading: LiveData<Boolean>
    val error: LiveData<ErrorModel>

    val selectedLocation: LiveData<Location>

    val openNavigationScreen: LiveData<Boolean>

    fun onStart()

    fun onButtonNavigateClick()
}