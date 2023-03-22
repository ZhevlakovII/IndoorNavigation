package com.izhxx.navui.domain.usecase.search

import com.izhxx.navcore.domain.model.SearchRequest
import io.reactivex.Single

internal interface SearchUseCase {
    fun insertRequest(request: SearchRequest)

    fun getRequests(): Single<List<SearchRequest>>
}