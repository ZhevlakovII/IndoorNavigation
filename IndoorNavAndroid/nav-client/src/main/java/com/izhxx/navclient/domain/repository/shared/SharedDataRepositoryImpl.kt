package com.izhxx.navclient.domain.repository.shared

import com.izhxx.navclient.domain.model.shared.SharedData
import javax.inject.Singleton

@Singleton
internal class SharedDataRepositoryImpl : SharedDataRepository {

    private val sharedData: SharedData = SharedData()

    override fun getSharedData(): SharedData = sharedData

}