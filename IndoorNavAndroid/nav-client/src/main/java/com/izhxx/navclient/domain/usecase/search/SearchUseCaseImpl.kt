package com.izhxx.navclient.domain.usecase.search

import com.izhxx.navcore.domain.model.SearchHistory
import com.izhxx.navcore.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

internal class SearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchUseCase {
    override fun insertRequest(request: SearchHistory) = searchRepository.insertRequest(request)

    override fun getRequests(): Single<List<SearchHistory>> = searchRepository.getRequests()

}