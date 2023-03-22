package com.izhxx.navcore.domain.model.location

/**
 * "Бизнес-модель" локации. Включает в себя
 * @param locationId ID локации карты
 * @param locationName Имя локации
 * @param coordinates координаты локации
 * @see MapCoordinates
 */
data class Location(
    val locationId: Int,
    val locationName: String,
    val coordinates: MapCoordinates
)