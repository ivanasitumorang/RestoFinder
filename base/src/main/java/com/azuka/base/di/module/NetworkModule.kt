package com.azuka.base.di.module

import android.content.Context
import com.azuka.base.BuildConfig
import com.azuka.base.external.SSLCertificateConfigurator
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val trustManagerFactory = SSLCertificateConfigurator.getTrustManager(context)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException(
                "Unexpected default trust managers:" + Arrays.toString(
                    trustManagers
                )
            )
        }
        val trustManager = trustManagers[0] as X509TrustManager
        return OkHttpClient.Builder()
            .sslSocketFactory(
                SSLCertificateConfigurator.getSSLConfiguration(context).socketFactory,
                trustManager
            )
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_ZOMATO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}