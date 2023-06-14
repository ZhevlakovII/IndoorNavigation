package com.izhxx.navclient.di.usecases

import com.izhxx.navcore.IndoorNavigationCore
import com.izhxx.navclient.domain.repository.shared.SharedDataRepository
import com.izhxx.navclient.domain.usecase.location.LocationUseCase
import com.izhxx.navclient.domain.usecase.location.LocationUseCaseImpl
import com.izhxx.navclient.domain.usecase.route.RouteUseCase
import com.izhxx.navclient.domain.usecase.route.RouteUseCaseImpl
import com.izhxx.navclient.domain.usecase.search.SearchUseCase
import com.izhxx.navclient.domain.usecase.search.SearchUseCaseImpl
import com.izhxx.navclient.domain.usecase.settings.SettingsUseCase
import com.izhxx.navclient.domain.usecase.settings.SettingsUseCaseImpl
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

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

    @Provides
    @Reusable
    fun provideRouteUseCase(sharedDataUseCase: SharedDataUseCase): RouteUseCase =
        RouteUseCaseImpl(sharedDataUseCase)

    @Provides
    @Singleton
    fun provideSharedDataUseCase(sharedDataRepository: SharedDataRepository): SharedDataUseCase =
        SharedDataUseCaseImpl(sharedDataRepository = sharedDataRepository)

}