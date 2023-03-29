package com.izhxx.navui.presentation.map.utils

import com.izhxx.navcore.domain.model.location.Location
import io.reactivex.Single

fun findLocation(x: Double, y: Double, locations: List<Location>): Single<Location> {
    return Single.just(Unit).map {
        var findLocation: Location? = null

        locations.forEach { location ->
            if (location.coordinates.matchAxisX(x) && location.coordinates.matchAxisY(y)) {
                findLocation = location
                findLocation?.coordinates?.currentAxisX = x
                findLocation?.coordinates?.currentAxisY = y
            }
        }

        findLocation ?: throw Error("Location not find")
    }
}