package com.azuka.restofinder.home.di

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.home.HomeActivity
import dagger.Component

 
@FeatureScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface HomeComponent : BaseComponent<HomeActivity> {
    @Component.Builder
    interface Builder {
        fun homeModule(homeModule: HomeModule): Builder
        fun appComponent(component: AppComponent): Builder
        fun build(): HomeComponent
    }
}