package com.izhxx.navui.domain.usecase.location

import com.izhxx.navcore.domain.model.location.Location
import io.reactivex.Single

internal interface LocationUseCase {
    fun insertLocation(location: Location)

    fun insertLocations(locationsList: List<Location>)

    fun getLocations(): Single<List<Location>>
}