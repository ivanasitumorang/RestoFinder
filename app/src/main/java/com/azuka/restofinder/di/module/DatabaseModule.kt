package com.azuka.restofinder.di.module

import android.content.Context
import androidx.room.Room
import com.azuka.restofinder.data.local.room.RestaurantDao
import com.azuka.restofinder.data.local.room.RestaurantDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideEncryptionDbSupportFactory(): SupportFactory {
        val passPhrase = SQLiteDatabase.getBytes("ivanaazuka".toCharArray())
        return SupportFactory(passPhrase)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context, supportFactory: SupportFactory): RestaurantDatabase =
        Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            "RestaurantDatabase.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(supportFactory)
            .build()

    @Singleton
    @Provides
    fun provideRestaurantDao(database: RestaurantDatabase): RestaurantDao = database.restaurantDao()
}