package com.izhxx.navcore.data.mappers.settings

import com.izhxx.navcore.data.model.UserSettingsEntity
import com.izhxx.navcore.domain.model.UserSettings

internal interface SettingsMapper {
    fun toEntity(settings: UserSettings): UserSettingsEntity

    fun toModel(settings: UserSettingsEntity): UserSettings
}