package com.azuka.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azuka.base.data.ErrorResponse


/**
 * Created by ivanaazuka on 30/09/20.
 * Android Engineer
 */
 
abstract class BaseViewModel : ViewModel() {
    protected val _loadingHandler = MutableLiveData<Boolean>()
    val loadingHandler: LiveData<Boolean> = _loadingHandler

    protected val _errorHandler = MutableLiveData<ErrorResponse>()
    val errorHandler: LiveData<ErrorResponse> = _errorHandler
}