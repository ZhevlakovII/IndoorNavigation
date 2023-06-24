package com.izhxx.navclient.domain.usecase.shared

import com.izhxx.navclient.domain.model.shared.SharedData

internal interface SharedDataUseCase {

    fun getSharedData(): SharedData
}