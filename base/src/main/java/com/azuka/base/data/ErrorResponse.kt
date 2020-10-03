package com.azuka.base.data


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

data class ErrorResponse(
    val code: Int = -1,
    val message: String? = "",
    val exception: Exception? = null
)