package com.izhxx.navcore.domain.repository

import com.izhxx.navcore.domain.model.SearchRequest
import io.reactivex.Single

interface SearchRepository {
    fun insertRequest(request: SearchRequest)

    fun getRequests(): Single<List<SearchRequest>>
}