package com.izhxx.navcore.data.mappers.search

import com.izhxx.navcore.data.model.SearchRequestEntity
import com.izhxx.navcore.domain.model.SearchRequest

internal interface SearchMapper {
    fun toEntity(request: SearchRequest): SearchRequestEntity

    fun toModel(request: List<SearchRequestEntity>): List<SearchRequest>
}