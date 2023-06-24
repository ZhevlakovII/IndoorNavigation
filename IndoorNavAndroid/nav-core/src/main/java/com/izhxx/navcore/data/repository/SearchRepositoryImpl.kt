package com.izhxx.navcore.data.repository

import com.izhxx.navcore.data.dao.SearchDao
import com.izhxx.navcore.data.mappers.search.SearchMapper
import com.izhxx.navcore.domain.model.SearchHistory
import com.izhxx.navcore.domain.repository.SearchRepository
import io.reactivex.Single

internal class SearchRepositoryImpl(
    private val searchDao: SearchDao,
    private val searchMapper: SearchMapper
) : SearchRepository {
    override fun insertRequest(request: SearchHistory) =
        searchDao.insertRequest(searchMapper.toEntity(request))

    override fun getRequests(): Single<List<SearchHistory>> =
        searchDao.getRequests().map { searchMapper.toModel(it) }

}