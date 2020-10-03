package com.azuka.restofinder.di.module

import com.azuka.base.presentation.widget.LoadingDialog
import com.azuka.restofinder.domain.repository.AppRepository
import com.azuka.restofinder.domain.usecase.AppUseCase
import com.azuka.restofinder.domain.usecase.AppUseCaseImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideLoadingDialog(): LoadingDialog = LoadingDialog.newInstance()

    @Singleton
    @Provides
    fun provideHomeUseCase(repository: AppRepository): AppUseCase = AppUseCaseImpl(repository)
}