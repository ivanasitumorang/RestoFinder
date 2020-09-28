package com.azuka.restofinder.feature

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.MainActivity
import com.azuka.restofinder.di.AppComponent
import dagger.Component


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */
 
@FeatureScope
@Component(dependencies = [AppComponent::class])
interface HomeComponent : BaseComponent<MainActivity> {
    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun build(): HomeComponent
    }
}