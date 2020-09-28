package com.azuka.base.di.component

import android.app.Activity
import android.app.Service

interface Component

interface BaseComponent<T> : Component {

    fun inject(target: T)
}

/**
 * Base dagger component for use in activities.
 */
interface BaseActivityComponent<T : Activity> :
    BaseComponent<T>

/**
 * Base dagger components for use in services.
 */
interface BaseServiceComponent<T : Service> :
    BaseComponent<T>
