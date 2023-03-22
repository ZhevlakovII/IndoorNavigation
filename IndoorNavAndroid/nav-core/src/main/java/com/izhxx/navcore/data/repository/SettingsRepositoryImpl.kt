package com.izhxx.navcore.data.repository

import com.izhxx.navcore.data.dao.SettingsDao
import com.izhxx.navcore.data.mappers.settings.SettingsMapper
import com.izhxx.navcore.domain.model.UserSettings
import com.izhxx.navcore.domain.repository.SettingsRepository
import io.reactivex.Single

internal class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao,
    private val settingsMapper: SettingsMapper
) : SettingsRepository {
    override fun insertUserSettings(settings: UserSettings) =
        settingsDao.insertUserSettings(settingsMapper.toEntity(settings))

    override fun getUserSettings(): Single<UserSettings> =
        settingsDao.getUserSettings().map { settingsMapper.toModel(it) }

}