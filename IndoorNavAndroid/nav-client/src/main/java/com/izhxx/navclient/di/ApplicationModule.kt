package com.izhxx.navclient.di

import com.izhxx.navclient.di.repository.RepositoryModule
import com.izhxx.navclient.di.usecases.UseCasesModule
import com.izhxx.navclient.di.viewmodels.ViewModelModule
import dagger.Module

@Module(includes = [RepositoryModule::class, UseCasesModule::class, ViewModelModule::class])
internal interface ApplicationModule