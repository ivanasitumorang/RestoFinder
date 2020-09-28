package com.azuka.base.external

import kotlin.coroutines.CoroutineContext

/**
 * Created by ivanaazuka on 03/03/20.
 * Android Engineer
 */

interface CoroutineContextProvider {
    fun mainThreadDispatcher(): CoroutineContext
    fun backgroundDispatcher(): CoroutineContext
}
