package com.azuka.base.di.component

/**
 * This class is used to inject non Android Component class
 * If you want to inject dagger in Non Activity class get your parent component via this class
 * */

object ShareComponent {
    lateinit var current: Component
}
