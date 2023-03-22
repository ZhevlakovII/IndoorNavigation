package com.izhxx.navcore.data.mappers.search

import com.izhxx.navcore.data.model.SearchRequestEntity
import com.izhxx.navcore.domain.model.SearchRequest

internal class SearchMapperImpl() : SearchMapper {
    override fun toEntity(request: SearchRequest): SearchRequestEntity =
        SearchRequestEntity(
            request.requestId,
            request.locationId
        )


    override fun toModel(request: List<SearchRequestEntity>): List<SearchRequest> =
        request.map {
            SearchRequest(
                it.requestId,
                it.locationId
            )
        }

}