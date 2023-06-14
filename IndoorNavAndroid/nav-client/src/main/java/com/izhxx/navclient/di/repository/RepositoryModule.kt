package com.izhxx.navclient.di.repository

import com.izhxx.navclient.domain.repository.shared.SharedDataRepository
import com.izhxx.navclient.domain.repository.shared.SharedDataRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideSharedDataRepository(): SharedDataRepository = SharedDataRepositoryImpl()
}