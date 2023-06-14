package com.izhxx.navclient.domain.model.error

import androidx.annotation.StringRes

internal class ErrorModel(
    val errorMessage: String? = null,
    @StringRes val errorMessageResource: Int? = null,
    val errorType: ErrorType,
    val timeError: Long = System.currentTimeMillis()
)