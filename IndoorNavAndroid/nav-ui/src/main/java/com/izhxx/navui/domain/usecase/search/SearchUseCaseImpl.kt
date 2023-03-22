package com.izhxx.navui.domain.usecase.search

import com.izhxx.navcore.domain.model.SearchRequest
import com.izhxx.navcore.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

internal class SearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchUseCase {
    override fun insertRequest(request: SearchRequest) = searchRepository.insertRequest(request)

    override fun getRequests(): Single<List<SearchRequest>> = searchRepository.getRequests()

}