package com.azuka.base.presentation

import com.azuka.base.di.component.Component


interface AbstractComponent {
    fun createComponent(): Component
}
