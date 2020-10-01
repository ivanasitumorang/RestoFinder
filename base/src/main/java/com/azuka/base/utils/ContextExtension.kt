package com.azuka.base.utils

import android.content.Context
import android.content.Intent


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

fun Context.goToScreen(clazz: Class<*>, bundle: (Intent.() -> Unit)? = null) {
    Intent(this, clazz).run {
        bundle?.invoke(this)
        startActivity(this)
    }
}