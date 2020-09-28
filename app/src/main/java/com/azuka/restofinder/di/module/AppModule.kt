package com.azuka.restofinder.di.module

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

    // todo : add repo
    // todo : add navigator
}