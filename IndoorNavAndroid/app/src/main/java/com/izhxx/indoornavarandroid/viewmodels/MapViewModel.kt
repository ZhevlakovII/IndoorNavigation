package com.izhxx.indoornavarandroid.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject internal constructor(
    application: Application
): ViewModel() {
    private val mapTiles = TileStreamProvider { row, col, zoomLvl ->
        try {
            application.applicationContext.assets.open("map_assets/$zoomLvl/$row/$col.png")
        } catch (e: Exception) {
            null
        }
    }

    val mapState: MapState by mutableStateOf(
        MapState(5, 7092, 7160).apply {
            addLayer(mapTiles)
            enableRotation()
        }
    )
}