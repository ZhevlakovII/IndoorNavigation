package com.izhxx.navcore.domain.repository

import com.izhxx.navcore.domain.model.SearchHistory
import io.reactivex.Single

interface SearchRepository {
    fun insertRequest(request: SearchHistory)

    fun getRequests(): Single<List<SearchHistory>>
}