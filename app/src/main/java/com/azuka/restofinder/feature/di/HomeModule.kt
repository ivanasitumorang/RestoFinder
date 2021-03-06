package com.azuka.restofinder.feature.di

import androidx.lifecycle.ViewModel
import com.azuka.base.di.viewmodel.ViewModelKey
import com.azuka.restofinder.domain.usecase.AppUseCase
import com.azuka.restofinder.feature.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */

@Module
class HomeModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(
        appUseCase: AppUseCase
    ): ViewModel =
        HomeViewModel(appUseCase)
}