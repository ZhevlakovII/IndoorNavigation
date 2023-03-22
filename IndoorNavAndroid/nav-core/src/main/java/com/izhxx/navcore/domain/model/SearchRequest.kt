package com.izhxx.navcore.domain.model

/**
 * Модель для запросов пользователя. Поиск состоит из двух параметров и строго зависит от ID локаций,
 * так как идёт привязка к ID из-за дешевезны поиска
 * @param requestId ID запроса, для сортировки
 * @param locationId ID локации, которую искали
 */
data class SearchRequest(
    val requestId: Int,
    val locationId: Int
)