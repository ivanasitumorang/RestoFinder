package com.azuka.restofinder.data.remote.network


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}