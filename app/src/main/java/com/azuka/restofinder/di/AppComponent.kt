package com.azuka.restofinder.di

import com.azuka.base.di.module.ContextModule
import com.azuka.base.di.module.CoreModule
import com.azuka.base.di.module.NetworkModule
import com.azuka.base.di.module.ViewModelModule
import com.azuka.base.external.CoroutineContextProvider
import com.azuka.restofinder.di.module.AppModule
import com.azuka.restofinder.di.module.AppNetworkModule
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */


@Singleton
@Component(
    modules = [
        ContextModule::class,
        AppModule::class,
        CoreModule::class,
        NetworkModule::class,
        AppNetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun contextModule(module: ContextModule): Builder
        fun build(): AppComponent
    }

    fun appCoroutineContextProvider(): CoroutineContextProvider
    fun networkProvider(): Retrofit
    fun gson(): Gson
}