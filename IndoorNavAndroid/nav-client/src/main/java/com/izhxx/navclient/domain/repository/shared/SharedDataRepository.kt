package com.izhxx.navclient.domain.repository.shared

import com.izhxx.navclient.domain.model.shared.SharedData

internal interface SharedDataRepository {

    fun getSharedData(): SharedData
}