package com.izhxx.navclient.domain.usecase.search

import com.izhxx.navcore.domain.model.SearchHistory
import io.reactivex.Single

internal interface SearchUseCase {
    fun insertRequest(request: SearchHistory)

    fun getRequests(): Single<List<SearchHistory>>
}