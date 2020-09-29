package com.azuka.restofinder.di.module

import com.azuka.restofinder.data.AppRepositoryImpl
import com.azuka.restofinder.domain.repository.AppRepository
import com.azuka.restofinder.domain.usecase.HomeUseCase
import com.azuka.restofinder.domain.usecase.HomeUseCaseImpl
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

//    @Singleton
//    @Provides
//    fun provideHomeUseCase(repository: AppRepository): HomeUseCase = HomeUseCaseImpl(repository)

    // todo : add repo
    // todo : add navigator
}