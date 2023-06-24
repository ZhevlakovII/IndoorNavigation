package com.izhxx.navclient.domain.usecase.route

import com.izhxx.navcore.domain.model.location.Location
import io.reactivex.Single
import ovh.plrapps.mapcompose.ui.paths.PathData
import ovh.plrapps.mapcompose.ui.paths.PathDataBuilder

internal interface RouteUseCase {

    fun createRoute(
        currentPosition: Location,
        destination: Location,
    ): Single<List<Location>>

    fun convertRouteToPath(
        route: List<Location>,
        pathDataBuilder: PathDataBuilder
    ): Single<PathData>
}