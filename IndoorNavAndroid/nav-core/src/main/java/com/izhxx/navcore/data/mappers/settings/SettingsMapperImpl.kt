package com.izhxx.navcore.data.mappers.settings

import com.izhxx.navcore.data.model.UserSettingsEntity
import com.izhxx.navcore.domain.model.UserSettings

internal class SettingsMapperImpl() : SettingsMapper {
    override fun toEntity(settings: UserSettings): UserSettingsEntity =
        UserSettingsEntity(settings.userId)

    override fun toModel(settings: UserSettingsEntity): UserSettings = UserSettings(settings.userId)

}