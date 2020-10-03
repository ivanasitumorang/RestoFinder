package com.azuka.base.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


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

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.openBrowser(link: String) {
    try {
        Intent(Intent.ACTION_VIEW).run {
            data = Uri.parse(link)
            startActivity(this)
        }
    } catch (e: Exception) {
        showToast("Not available right now")
    }
}