package com.azuka.base.data

import androidx.annotation.Keep


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

@Keep
data class ErrorResponse(
    val code: Int = -1,
    val message: String? = "",
    val exception: Exception? = null
)