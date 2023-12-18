package com.izhxx.navclient.presentation.route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.izhxx.navshared.base.NavViewModel
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.domain.usecase.route.RouteUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.addPath
import ovh.plrapps.mapcompose.api.makePathDataBuilder
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.paths.PathData
import ovh.plrapps.mapcompose.ui.state.MapState
import javax.inject.Inject

internal class RouteViewModelImpl @Inject constructor(
    private val routeUseCase: RouteUseCase,
    private val sharedDataUseCase: SharedDataUseCase,
    private val tilesProvider: TileStreamProvider
) : NavViewModel(), RouteViewModel {

    override val mapState: MapState by mutableStateOf(
        MapState(
            levelCount = 5,
            fullWidth = 3546,
            fullHeight = 3580,
            tileSize = 256
        ).apply {
            addLayer(tilesProvider)
        }
    )

    override val isLoading = MutableLiveData<Boolean>()
    override val error = MutableLiveData<ErrorModel>()

    companion object {
        private const val PATH_ID = "NavUiUserPath"
    }

    override fun onStart() {
        routeUseCase.convertRouteToPath(
            route = sharedDataUseCase.getSharedData().route ?: emptyList(),
            pathDataBuilder = mapState.makePathDataBuilder()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showPath(it)
                },
                {
                    error.value = ErrorModel(
                        errorMessage = it.localizedMessage ?: "",
                        errorType = ErrorType.SCREEN_SHOWS
                    )
                }
            )
            .untilCleared()
    }

    private fun showPath(pathData: PathData) {
        mapState.addPath(
            id = PATH_ID,
            pathData = pathData,
            color = Color(com.izhxx.navuikit.R.color.nav_uikit_interface_tertiary),
            width = 10.dp
        )
    }

}