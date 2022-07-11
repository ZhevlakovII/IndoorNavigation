package com.izhxx.indoornavarandroid.viewmodels

import android.app.Application
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import com.izhxx.indoornavarandroid.R
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.LocationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import ovh.plrapps.mapcompose.api.*
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject internal constructor(
    private val application: Application,
    private val locationsRepo: LocationRepo
): ViewModel() {
    val locations = locationsRepo.getAllLocations().asLiveData() //Locations list

    private val mapPoints: MutableList<Location> = mutableListOf() //List for the location selected by clicking

    //Value for observer map onTap function
    val isTapOnMap = MutableLiveData<Boolean>().apply { value = false }
    private var isTapActive: Boolean = false

    //Create value for TileStreamProvider (opening and displaying map tiles)
    private val mapTiles = TileStreamProvider { row, col, zoomLvl ->
        try {
            application.applicationContext.assets.open("mapAssets/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }

    //Create state for MapCompose
    val mapState: MapState by mutableStateOf(
        MapState(5 , 3546, 3580, tileSize = 256).apply {
            addLayer(mapTiles)
            enableRotation()
            onTap { x, y ->
                if (isTapActive) {
                    mapClickHandler(x, y)
                }
            }
        }
    )

    fun changeTapState(isNextTap: Boolean) {
        isTapOnMap.value = !isNextTap
    }

    fun changeTapActivate(result: Boolean) {
        isTapActive = result
    }

    private fun mapClickHandler(x: Double, y: Double) {
        if (mapPoints.count() == 1) {
            mapState.removeMarker("${mapPoints.lastIndex}")
            mapPoints.removeLast()
            changeTapState(true)
        } else if (mapPoints.isEmpty()) {
            val location = validateCoordinates(x, y)

            if (location != null) {
                mapPoints.add(location)

                mapState.addMarker("${mapPoints.lastIndex}", x, y) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_map_pin_24dp),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color(R.color.interface_primary)
                    )
                }

                changeTapState(false)
            }
        }
    }

    private fun validateCoordinates(x: Double, y: Double): Location? {
        locations.value?.forEach { loc ->
            if (
                (loc.startX <= x && loc.endX >= x) &&
                (loc.startY <= y && loc.endY >= y)
            )
                return loc
        }

        return null
    }

    fun getLocation(): Location {
        return mapPoints[mapPoints.lastIndex]
    }

    fun getLocationId(): Int {
        return mapPoints[mapPoints.lastIndex].locationId
    }
}