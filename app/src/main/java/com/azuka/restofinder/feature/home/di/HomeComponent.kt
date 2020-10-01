package com.azuka.restofinder.feature.home.di

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.feature.di.HomeModule
import com.azuka.restofinder.feature.home.HomeActivity
import dagger.Component

 
@FeatureScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface HomeComponent : BaseComponent<HomeActivity> {
    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun build(): HomeComponent
    }
}