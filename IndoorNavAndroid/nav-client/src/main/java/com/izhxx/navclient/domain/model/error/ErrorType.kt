package com.izhxx.navclient.domain.model.error

internal enum class ErrorType {
    TOAST_SHOWS,
    SNACKBAR_SHOWS,
    SCREEN_SHOWS,
    UNKNOWN;

    fun getError(errorName: String): ErrorType {
        val errorsList = ErrorType.values()

        return errorsList.firstOrNull { it.name.lowercase() == errorName.lowercase() } ?: UNKNOWN
    }
}