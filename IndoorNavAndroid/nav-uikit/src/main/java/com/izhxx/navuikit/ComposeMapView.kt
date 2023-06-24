package com.izhxx.navuikit

import androidx.compose.runtime.Composable
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun ComposeMapView(
    mapState: MapState
) {
    MapUI(state = mapState)
}