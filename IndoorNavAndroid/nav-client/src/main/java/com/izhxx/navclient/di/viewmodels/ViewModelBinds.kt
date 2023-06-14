package com.izhxx.navclient.di.viewmodels

import com.izhxx.navclient.presentation.SharedViewModel
import com.izhxx.navclient.presentation.SharedViewModelImpl
import com.izhxx.navclient.presentation.map.MapViewModel
import com.izhxx.navclient.presentation.map.MapViewModelImpl
import com.izhxx.navclient.presentation.navigation.NavigationViewModel
import com.izhxx.navclient.presentation.navigation.NavigationViewModelImpl
import com.izhxx.navclient.presentation.route.RouteViewModel
import com.izhxx.navclient.presentation.route.RouteViewModelImpl
import com.izhxx.navclient.presentation.search.SearchViewModel
import com.izhxx.navclient.presentation.search.SearchViewModelImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ViewModelBinds {

    @Binds
    fun bindMapViewModelInterface(mapViewModelImpl: MapViewModelImpl): MapViewModel

    @Binds
    fun bindSearchViewModelInterface(searchViewModelImpl: SearchViewModelImpl): SearchViewModel

    @Binds
    fun bindNavigationViewModelInterface(navigationViewModelImpl: NavigationViewModelImpl): NavigationViewModel

    @Binds
    fun bindRouteViewModelInterface(routeViewModelImpl: RouteViewModelImpl): RouteViewModel

    @Binds
    fun bindSharedViewModelInterface(sharedViewModelImpl: SharedViewModelImpl): SharedViewModel
}