package com.azuka.restofinder.data

import com.azuka.base.data.ErrorResponse


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorData: ErrorResponse? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(errorData: ErrorResponse? = null) :
        Resource<T>(data = null, errorData = errorData)
}