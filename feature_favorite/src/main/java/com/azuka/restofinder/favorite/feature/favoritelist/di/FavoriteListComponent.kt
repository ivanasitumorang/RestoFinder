package com.azuka.restofinder.favorite.feature.favoritelist.di

import com.azuka.base.di.component.BaseComponent
import com.azuka.base.di.scope.FeatureScope
import com.azuka.restofinder.di.AppComponent
import com.azuka.restofinder.favorite.di.FavoriteModule
import com.azuka.restofinder.favorite.feature.favoritelist.FavoriteListActivity
import dagger.Component


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

@FeatureScope
@Component(modules = [FavoriteModule::class], dependencies = [AppComponent::class])
interface FavoriteListComponent : BaseComponent<FavoriteListActivity> {
    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun build(): FavoriteListComponent
    }
}