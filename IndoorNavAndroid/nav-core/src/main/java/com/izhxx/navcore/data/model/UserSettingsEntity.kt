package com.izhxx.navcore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.izhxx.navcore.data.model.UserSettingsEntity.Companion.SETTINGS_TABLE_NAME

@Entity(tableName = SETTINGS_TABLE_NAME)
internal data class UserSettingsEntity(
    @PrimaryKey
    val userId: String
) {
    companion object {
        internal const val SETTINGS_TABLE_NAME = "settings_table"
    }

}
