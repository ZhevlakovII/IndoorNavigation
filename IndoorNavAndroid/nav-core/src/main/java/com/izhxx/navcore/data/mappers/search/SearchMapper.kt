package com.izhxx.navcore.data.mappers.search

import com.izhxx.navcore.data.model.SearchHistoryEntity
import com.izhxx.navcore.domain.model.SearchHistory

internal interface SearchMapper {
    fun toEntity(request: SearchHistory): SearchHistoryEntity

    fun toModel(request: List<SearchHistoryEntity>): List<SearchHistory>
}