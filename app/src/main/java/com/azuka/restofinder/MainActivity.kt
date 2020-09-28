package com.azuka.restofinder

import android.os.Bundle
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivity
import com.azuka.restofinder.feature.DaggerHomeComponent
import com.azuka.restofinder.feature.HomeComponent
import com.google.gson.Gson
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var gson: Gson

    private lateinit var component: HomeComponent

    override fun getLayoutId() = R.layout.activity_main

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun createComponent(): Component {
        component = DaggerHomeComponent.builder()
            .appComponent(appComponent())
            .build()
        return component
    }
}