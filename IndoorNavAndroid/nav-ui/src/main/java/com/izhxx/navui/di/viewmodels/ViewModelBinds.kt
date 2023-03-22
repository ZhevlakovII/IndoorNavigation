package com.izhxx.navui.di.viewmodels

import com.izhxx.navui.presentation.map.MapViewModel
import com.izhxx.navui.presentation.map.MapViewModelImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ViewModelBinds {

    @Binds
    fun bindMapViewModelInterface(mapViewModelImpl: MapViewModelImpl): MapViewModel
}