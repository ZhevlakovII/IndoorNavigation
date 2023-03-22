package com.izhxx.navcore.data.dao

import androidx.room.*
import com.izhxx.navcore.data.model.SearchRequestEntity
import com.izhxx.navcore.data.model.SearchRequestEntity.Companion.SEARCH_TABLE_NAME
import io.reactivex.Single

@Dao
internal interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(searchRequest: SearchRequestEntity)

    @Query("SELECT * FROM $SEARCH_TABLE_NAME")
    fun getRequests(): Single<List<SearchRequestEntity>>
}