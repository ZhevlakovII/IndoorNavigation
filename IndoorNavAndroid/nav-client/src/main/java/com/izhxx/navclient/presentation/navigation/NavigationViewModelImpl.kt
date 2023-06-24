package com.izhxx.navclient.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.izhxx.navclient.R
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navshared.base.NavViewModel
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.domain.usecase.route.RouteUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import com.izhxx.navclient.utils.mapProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import javax.inject.Inject

internal class NavigationViewModelImpl @Inject constructor(
    private val routeUseCase: RouteUseCase,
    private val sharedDataUseCase: SharedDataUseCase,
) : NavViewModel(), NavigationViewModel {

    private val mapTiles: TileStreamProvider
        get() = mapProvider()
    override val mapState: MapState by mutableStateOf(
        MapState(
            levelCount = 5,
            fullWidth = 3546,
            fullHeight = 3580,
            tileSize = 256
        ).apply {
            addLayer(mapTiles)
        }
    )

    override val isLoading = MutableLiveData<Boolean>()
    override val error = MutableLiveData<ErrorModel>()

    override val currentPosition = MutableLiveData<Location>()
    override val destination = MutableLiveData<Location>()

    override val openRouteScreen = MutableLiveData<Boolean>()

    override fun onStart() {
        sharedDataUseCase.getSharedData().destination?.let { destination.value = it }
        sharedDataUseCase.getSharedData().currentUserPosition?.let { currentPosition.value = it }
    }

    override fun onCurrentPositionButtonClick() {
        sharedDataUseCase.getSharedData().isSetDestination = false
    }

    override fun onDestinationButtonClick() {
        sharedDataUseCase.getSharedData().isSetDestination = true
    }

    override fun onNavigateButtonClick() {
        val curPos = currentPosition.value
        val dest = destination.value

        if (curPos == null && dest == null) {
            error.value = getError(R.string.nav_client_navigation_error_points_empty)
            return
        }

        if (curPos == null) {
            error.value = getError(R.string.nav_client_navigation_error_current_position_empty)
            return
        }
        if (dest == null) {
            error.value = getError(R.string.nav_client_navigation_error_destination_empty)
            return
        }

        createRoute(curPos, dest)
    }

    private fun getError(@StringRes messageRes: Int): ErrorModel = ErrorModel(
        errorMessageResource = messageRes,
        errorType = ErrorType.SCREEN_SHOWS
    )

    private fun createRoute(
        curPos: Location,
        dest: Location,
    ) {
        routeUseCase.createRoute(curPos, dest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe(
                {
                    sharedDataUseCase.getSharedData().route = it
                    openRouteScreen.value = true
                },
                {
                    error.value = ErrorModel(
                        errorMessage = it.localizedMessage ?: "",
                        errorType = ErrorType.TOAST_SHOWS
                    )
                }
            )
            .untilCleared()
    }

}