package com.izhxx.navcore.domain.repository

import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.utils.LocationsDomain
import io.reactivex.Single

interface LocationRepository {
    fun insertLocation(location: Location)

    fun insertLocations(locationsList: List<Location>)

    fun getLocations(): Single<LocationsDomain>

    fun getLocationById(locationId: Int): Single<Location>

    fun findLocationByOrdinates(x: Double, y: Double): Single<Location>

    fun findLocationByName(name: String): Single<LocationsDomain>
}