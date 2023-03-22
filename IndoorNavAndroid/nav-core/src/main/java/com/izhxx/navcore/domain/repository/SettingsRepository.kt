package com.izhxx.navcore.domain.repository

import com.izhxx.navcore.domain.model.UserSettings
import io.reactivex.Single

interface SettingsRepository {
    fun insertUserSettings(settings: UserSettings)

    fun getUserSettings(): Single<UserSettings>
}