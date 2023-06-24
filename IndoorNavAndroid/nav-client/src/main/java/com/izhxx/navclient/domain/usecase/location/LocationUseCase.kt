package com.izhxx.navclient.domain.usecase.location

import com.izhxx.navcore.domain.model.location.Location
import io.reactivex.Single

internal interface LocationUseCase {
    fun insertLocation(location: Location)

    fun insertLocations(locationsList: List<Location>)

    fun getLocations(): Single<List<Location>>

    fun getLocationById(locationId: Int): Single<Location>

    fun findLocationByOrdinates(x: Double, y: Double): Single<Location>

    fun findLocationByName(name: String): Single<List<Location>>
}