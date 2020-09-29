package com.azuka.restofinder.di.module

import com.azuka.restofinder.data.remote.network.AppNetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */
 
@Module
class AppNetworkModule {
    @Provides
    @Singleton
    fun provideAppNetworkService(restAdapter: Retrofit): AppNetworkService {
        return restAdapter.create(AppNetworkService::class.java)
    }
}