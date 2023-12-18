package com.izhxx.navclient.di

import android.content.Context
import com.izhxx.navclient.presentation.ClientActivity
import com.izhxx.navclient.presentation.map.MapFragment
import com.izhxx.navclient.presentation.navigation.NavigationFragment
import com.izhxx.navclient.presentation.route.RouteFragment
import com.izhxx.navclient.presentation.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import ovh.plrapps.mapcompose.core.TileStreamProvider
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
internal interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder
        @BindsInstance
        fun bindTileProvider(tilesProvider: TileStreamProvider): Builder

        fun build(): ApplicationComponent
    }

    fun inject(clientActivity: ClientActivity)
    fun inject(mapFragment: MapFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(navigationFragment: NavigationFragment)
    fun inject(routeFragment: RouteFragment)
}