package com.izhxx.navui.presentation.map.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
internal fun MapComposeView(
    modifier: Modifier,
    state: MapState
) {
    MapUI(modifier = modifier, state = state)
}