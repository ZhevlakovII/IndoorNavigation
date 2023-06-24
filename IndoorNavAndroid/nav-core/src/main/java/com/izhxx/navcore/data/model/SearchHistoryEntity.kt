package com.izhxx.navcore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.izhxx.navcore.data.model.SearchHistoryEntity.Companion.SEARCH_TABLE_NAME

@Entity(tableName = SEARCH_TABLE_NAME)
internal data class SearchHistoryEntity(
    @PrimaryKey
    val requestId: Int,
    val locationId: Int
) {
    companion object {
        internal const val SEARCH_TABLE_NAME = "search_table"
    }

}
