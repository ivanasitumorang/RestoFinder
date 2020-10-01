package com.azuka.restofinder.feature.detail.di

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.feature.detail.DetailActivity
import com.azuka.restofinder.feature.di.HomeModule
import com.azuka.restofinder.feature.home.di.HomeComponent
import dagger.Component


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

@FeatureScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface DetailComponent : BaseComponent<DetailActivity> {
    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun build(): DetailComponent
    }
}