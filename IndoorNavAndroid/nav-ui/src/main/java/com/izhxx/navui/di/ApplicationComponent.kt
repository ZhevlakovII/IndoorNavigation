package com.izhxx.navui.di

import android.content.Context
import com.izhxx.navui.presentation.MainActivity
import com.izhxx.navui.presentation.map.MapFragment
import com.izhxx.navui.presentation.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
internal interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mapFragment: MapFragment)
    fun inject(searchFragment: SearchFragment)
}