package com.azuka.restofinder.di.module

import com.azuka.base.external.CoroutineContextProvider
import com.azuka.restofinder.data.remote.RemoteDataSource
import com.azuka.restofinder.data.remote.RemoteDataSourceImpl
import com.azuka.restofinder.data.AppRepositoryImpl
import com.azuka.restofinder.data.local.LocalDataSource
import com.azuka.restofinder.data.local.LocalDataSourceImpl
import com.azuka.restofinder.data.local.room.RestaurantDao
import com.azuka.restofinder.data.remote.network.AppNetworkService
import com.azuka.restofinder.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */

@Module
class RepositoryModule {


    @Singleton
    @Provides
    fun provideLocalDataSource(
        restaurantDao: RestaurantDao,
        coroutineContextProvider: CoroutineContextProvider
    ): LocalDataSource =
        LocalDataSourceImpl(restaurantDao, coroutineContextProvider)

    @Singleton
    @Provides
    fun provideRemoteDataSource(networkService: AppNetworkService): RemoteDataSource =
        RemoteDataSourceImpl(networkService)

    @Singleton
    @Provides
    fun provideAppRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): AppRepository =
        AppRepositoryImpl(localDataSource, remoteDataSource)
}