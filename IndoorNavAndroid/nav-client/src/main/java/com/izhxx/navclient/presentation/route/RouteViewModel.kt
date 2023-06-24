package com.izhxx.navclient.presentation.route

import androidx.lifecycle.LiveData
import com.izhxx.navclient.domain.model.error.ErrorModel
import ovh.plrapps.mapcompose.ui.state.MapState

internal interface RouteViewModel {

    val mapState: MapState

    val isLoading: LiveData<Boolean>
    val error: LiveData<ErrorModel>

    fun onStart()
}