package com.izhxx.navcreator.di

import android.content.Context
import com.izhxx.navcreator.presentation.CreatorActivity
import com.izhxx.navcreator.presentation.map.MapFragment
import com.izhxx.navcreator.presentation.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
internal interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(instance: CreatorActivity)
    fun inject(instance: MapFragment)
    fun inject(instance: SettingsFragment)
}