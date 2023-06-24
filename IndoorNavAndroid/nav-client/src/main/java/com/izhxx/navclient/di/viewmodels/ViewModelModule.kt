package com.izhxx.navclient.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izhxx.navshared.dagger.NavDaggerViewModelFactory
import com.izhxx.navshared.dagger.NavDaggerViewModelKey
import com.izhxx.navclient.presentation.SharedViewModelImpl
import com.izhxx.navclient.presentation.map.MapViewModelImpl
import com.izhxx.navclient.presentation.navigation.NavigationViewModelImpl
import com.izhxx.navclient.presentation.route.RouteViewModelImpl
import com.izhxx.navclient.presentation.search.SearchViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelBinds::class])
internal interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(customFactory: NavDaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @NavDaggerViewModelKey(MapViewModelImpl::class)
    fun bindMapViewModel(mapViewModelImpl: MapViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @NavDaggerViewModelKey(SearchViewModelImpl::class)
    fun bindSearchViewModel(searchViewModelImpl: SearchViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @NavDaggerViewModelKey(NavigationViewModelImpl::class)
    fun bindNavigationViewModel(navigationViewModelImpl: NavigationViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @NavDaggerViewModelKey(RouteViewModelImpl::class)
    fun bindRouteViewModel(routeViewModelImpl: RouteViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @NavDaggerViewModelKey(SharedViewModelImpl::class)
    fun bindSharedViewModel(sharedViewModelImpl: SharedViewModelImpl): ViewModel
}