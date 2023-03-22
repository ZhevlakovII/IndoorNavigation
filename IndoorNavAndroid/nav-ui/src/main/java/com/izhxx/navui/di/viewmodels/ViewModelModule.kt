package com.izhxx.navui.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izhxx.navui.presentation.map.MapViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(customFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModelImpl::class)
    fun bindMapViewModel(mapViewModelImpl: MapViewModelImpl): ViewModel
}