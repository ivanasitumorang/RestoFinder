package com.azuka.restofinder

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.azuka.base.di.module.ContextModule
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.di.DaggerAppComponent


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */

class RestoApp : Application() {

    companion object {
        @JvmStatic
        fun appComponent(context: Context) =
            (context.applicationContext as RestoApp).appComponent

        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}

fun Activity.appComponent() = RestoApp.appComponent(this)