package com.azuka.restofinder.favorite.feature.detail.di

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.favorite.di.FavoriteModule
import com.azuka.restofinder.favorite.feature.detail.DetailActivity
import dagger.Component


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

@FeatureScope
@Component(modules = [FavoriteModule::class], dependencies = [AppComponent::class])
interface DetailComponent : BaseComponent<DetailActivity> {
    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun build(): DetailComponent
    }
}