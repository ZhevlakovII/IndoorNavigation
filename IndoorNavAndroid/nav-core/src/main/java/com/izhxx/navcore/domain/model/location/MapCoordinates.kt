package com.izhxx.navcore.domain.model.location

/**
 * Дата-класс для координа локации. Применяется на карту и зависит от её размеров
 * @param startAxisX - Стартовая координата по оХ/оY
 * @param endAxisX - Конечная координата по оХ/oY
 * @param centerAxisX - Точка в центре локации.
 * Координаты задаются как по оси Х, так и по оси Y.
 */
data class MapCoordinates(
    val startAxisX: Double,
    val centerAxisX: Double,
    val endAxisX: Double,
    val startAxisY: Double,
    val centerAxisY: Double,
    val endAxisY: Double,
    var currentAxisX: Double? = null,
    var currentAxisY: Double? = null
) {
    fun matchAxisX(x: Double): Boolean = x in startAxisX..endAxisX
    fun matchAxisY(y: Double): Boolean = y in startAxisY..endAxisY

}