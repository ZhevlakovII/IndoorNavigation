package com.izhxx.navcore.data.dao

import androidx.room.*
import com.izhxx.navcore.data.model.SearchHistoryEntity
import com.izhxx.navcore.data.model.SearchHistoryEntity.Companion.SEARCH_TABLE_NAME
import io.reactivex.Single

@Dao
internal interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(searchRequest: SearchHistoryEntity)

    @Query("SELECT * FROM $SEARCH_TABLE_NAME")
    fun getRequests(): Single<List<SearchHistoryEntity>>
}