package com.izhxx.navcore.data.dao

import androidx.room.*
import com.izhxx.navcore.data.model.UserSettingsEntity
import com.izhxx.navcore.data.model.UserSettingsEntity.Companion.SETTINGS_TABLE_NAME
import io.reactivex.Single

@Dao
internal interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserSettings(settings: UserSettingsEntity)

    @Query("SELECT * FROM $SETTINGS_TABLE_NAME")
    fun getUserSettings(): Single<UserSettingsEntity>
}