package com.azuka.base.external

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


class AppCoroutineContextProvider : CoroutineContextProvider {
    override fun mainThreadDispatcher(): CoroutineContext = Dispatchers.Main
    override fun backgroundDispatcher(): CoroutineContext = Dispatchers.IO
}
