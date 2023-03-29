package com.izhxx.navcore.domain.repository

import com.izhxx.navcore.domain.model.location.Location
import io.reactivex.Single

interface LocationRepository {
    fun insertLocation(location: Location)

    fun insertLocations(locationsList: List<Location>)

    fun getLocations(): Single<List<Location>>
}