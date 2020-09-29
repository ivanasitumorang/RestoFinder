package com.azuka.restofinder.home

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
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