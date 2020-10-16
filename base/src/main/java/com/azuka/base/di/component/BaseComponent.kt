package com.azuka.base.di.component

interface Component

interface BaseComponent<T> : Component {

    fun inject(target: T)
}

