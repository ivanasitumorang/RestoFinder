package com.azuka.base.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(val application: Application) {
    @Singleton
    @Provides
    fun provideContext(): Context = application
}
