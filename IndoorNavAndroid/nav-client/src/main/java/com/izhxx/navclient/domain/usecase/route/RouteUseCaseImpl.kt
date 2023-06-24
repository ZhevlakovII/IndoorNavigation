package com.izhxx.navclient.domain.usecase.route

import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import io.reactivex.Single
import ovh.plrapps.mapcompose.ui.paths.PathData
import ovh.plrapps.mapcompose.ui.paths.PathDataBuilder
import javax.inject.Inject

internal class RouteUseCaseImpl @Inject constructor(
    private val sharedDataUseCase: SharedDataUseCase,
) : RouteUseCase {

    override fun createRoute(
        currentPosition: Location,
        destination: Location,
    ): Single<List<Location>> = Single
        .fromCallable {
            return@fromCallable findRouteUseAStar(currentPosition, destination)
        }

    override fun convertRouteToPath(
        route: List<Location>,
        pathDataBuilder: PathDataBuilder
    ): Single<PathData> = Single
        .fromCallable {
            for (point in route) {
                pathDataBuilder.addPoint(
                    x = point.coordinates.currentAxisX ?: point.coordinates.centerAxisX,
                    y = point.coordinates.currentAxisY ?: point.coordinates.centerAxisY
                )
            }

            return@fromCallable pathDataBuilder.build() ?: throw Error("Can't create map path")
        }

    private fun findRouteUseAStar(
        currentPosition: Location,
        destination: Location,
    ): List<Location> {
        val route: MutableList<Location> = mutableListOf()
        val pointsList = sharedDataUseCase.getSharedData().locationList

        if (pointsList.isNullOrEmpty()) throw Error("Locations is empty")

        //realize AStar


        return route
    }

}