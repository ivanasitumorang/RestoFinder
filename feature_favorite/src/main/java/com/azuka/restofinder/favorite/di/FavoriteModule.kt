package com.azuka.restofinder.favorite.di

import androidx.lifecycle.ViewModel
import com.azuka.base.di.viewmodel.ViewModelKey
import com.azuka.restofinder.domain.usecase.AppUseCase
import com.azuka.restofinder.favorite.feature.FavoriteViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

@Module
class FavoriteModule {
    @Provides
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun provideFavoriteViewModel(
        appUseCase: AppUseCase
    ): ViewModel =
        FavoriteViewModel(appUseCase)
}