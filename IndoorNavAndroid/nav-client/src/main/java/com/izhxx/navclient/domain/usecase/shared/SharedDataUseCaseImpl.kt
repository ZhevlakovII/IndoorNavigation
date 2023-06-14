package com.izhxx.navclient.domain.usecase.shared

import com.izhxx.navclient.domain.model.shared.SharedData
import com.izhxx.navclient.domain.repository.shared.SharedDataRepository
import javax.inject.Inject

internal class SharedDataUseCaseImpl @Inject constructor(
    private val sharedDataRepository: SharedDataRepository
) : SharedDataUseCase {

    override fun getSharedData(): SharedData = sharedDataRepository.getSharedData()

}