package com.izhxx.navclient.presentation

import androidx.lifecycle.MutableLiveData
import com.izhxx.navshared.base.NavViewModel
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.domain.usecase.location.LocationUseCase
import com.izhxx.navclient.domain.usecase.shared.SharedDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SharedViewModelImpl @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val sharedDataUseCase: SharedDataUseCase
) : NavViewModel(), SharedViewModel {

    override val error = MutableLiveData<ErrorModel>()

    override fun fetchLocations() {
        locationUseCase
            .getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { sharedDataUseCase.getSharedData().locationList = it },
                {
                    error.value = ErrorModel(
                        errorMessage = it.localizedMessage,
                        errorType = ErrorType.TOAST_SHOWS
                    )
                }
            )
            .untilCleared()
    }

}