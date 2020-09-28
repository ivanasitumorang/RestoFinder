package com.azuka.base.di.module

import com.azuka.base.external.AppCoroutineContextProvider
import com.azuka.base.external.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider = AppCoroutineContextProvider()
}