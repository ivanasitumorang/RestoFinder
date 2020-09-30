package com.azuka.restofinder.feature.home.di

import androidx.lifecycle.ViewModel
import com.azuka.base.di.scope.FeatureScope
import com.azuka.base.di.viewmodel.ViewModelKey
import com.azuka.restofinder.domain.repository.AppRepository
import com.azuka.restofinder.domain.usecase.HomeUseCase
import com.azuka.restofinder.domain.usecase.HomeUseCaseImpl
import com.azuka.restofinder.feature.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */

@Module
class HomeModule {

    @FeatureScope
    @Provides
    fun provideHomeUseCase(repository: AppRepository): HomeUseCase = HomeUseCaseImpl(repository)

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(
        homeUseCase: HomeUseCase
    ): ViewModel =
        HomeViewModel(homeUseCase)
}