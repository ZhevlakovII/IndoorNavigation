package com.izhxx.navclient.presentation.search.model

internal data class SearchHistoryPresentation(
    val requestId: Int,
    val locationId: Int,
    val locationName: String
)