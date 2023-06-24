package com.izhxx.navcore.data.mappers.search

import com.izhxx.navcore.data.model.SearchHistoryEntity
import com.izhxx.navcore.domain.model.SearchHistory

internal class SearchMapperImpl() : SearchMapper {
    override fun toEntity(request: SearchHistory): SearchHistoryEntity =
        SearchHistoryEntity(
            request.requestId,
            request.locationId
        )


    override fun toModel(request: List<SearchHistoryEntity>): List<SearchHistory> =
        request.map {
            SearchHistory(
                it.requestId,
                it.locationId
            )
        }

}