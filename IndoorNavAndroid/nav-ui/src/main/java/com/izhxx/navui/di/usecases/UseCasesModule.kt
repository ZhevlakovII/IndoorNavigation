package com.izhxx.navui.di.usecases

import com.izhxx.navcore.IndoorNavigationCore
import com.izhxx.navui.domain.usecase.location.LocationUseCase
import com.izhxx.navui.domain.usecase.location.LocationUseCaseImpl
import com.izhxx.navui.domain.usecase.search.SearchUseCase
import com.izhxx.navui.domain.usecase.search.SearchUseCaseImpl
import com.izhxx.navui.domain.usecase.settings.SettingsUseCase
import com.izhxx.navui.domain.usecase.settings.SettingsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal class UseCasesModule {

    @Provides
    @Reusable
    fun provideLocationUseCase(): LocationUseCase = LocationUseCaseImpl(
        IndoorNavigationCore.getInstance().getLocationRepository()
    )

    @Provides
    @Reusable
    fun provideSearchUseCase(): SearchUseCase = SearchUseCaseImpl(
        IndoorNavigationCore.getInstance().getSearchRepository()
    )

    @Provides
    @Reusable
    fun provideSettingsUseCase(): SettingsUseCase = SettingsUseCaseImpl(
        IndoorNavigationCore.getInstance().getSettingsRepository()
    )

}