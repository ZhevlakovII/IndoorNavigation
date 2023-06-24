package com.izhxx.navclient.domain.usecase.settings

import com.izhxx.navcore.domain.model.UserSettings
import io.reactivex.Single

internal interface SettingsUseCase {
    fun insertUserSettings(settings: UserSettings)

    fun getUserSettings(): Single<UserSettings>
}