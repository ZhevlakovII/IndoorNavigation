package com.izhxx.navui.domain.usecase.settings

import com.izhxx.navcore.domain.model.UserSettings
import com.izhxx.navcore.domain.repository.SettingsRepository
import io.reactivex.Single
import javax.inject.Inject

internal class SettingsUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : SettingsUseCase {
    override fun insertUserSettings(settings: UserSettings) =
        settingsRepository.insertUserSettings(settings)

    override fun getUserSettings(): Single<UserSettings> = settingsRepository.getUserSettings()

}