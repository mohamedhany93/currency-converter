package com.spotspal.fanartec.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel()  {

    protected var responseError = MutableLiveData<String>()

    public var errorObserved = false

    fun getApiResponseError(): MutableLiveData<String> {
        if (!errorObserved) {
            errorObserved = true
        }
        return responseError
    }

    protected var responseValidation = MutableLiveData<String>()

    fun getApiResponseValidation(): MutableLiveData<String> {
        return responseValidation
    }

}