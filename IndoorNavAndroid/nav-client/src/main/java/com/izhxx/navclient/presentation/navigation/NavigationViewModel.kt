package com.izhxx.navclient.presentation.navigation

import androidx.lifecycle.LiveData
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navclient.domain.model.error.ErrorModel
import ovh.plrapps.mapcompose.ui.state.MapState

internal interface NavigationViewModel {

    val mapState: MapState

    val isLoading: LiveData<Boolean>
    val error: LiveData<ErrorModel>

    val currentPosition: LiveData<Location>
    val destination: LiveData<Location>

    val openRouteScreen: LiveData<Boolean>

    fun onStart()

    fun onCurrentPositionButtonClick()

    fun onDestinationButtonClick()

    fun onNavigateButtonClick()
}