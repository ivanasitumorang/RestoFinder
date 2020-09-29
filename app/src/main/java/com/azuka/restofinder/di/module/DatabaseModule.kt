package com.azuka.restofinder.di.module

import android.content.Context
import androidx.room.Room
import com.azuka.restofinder.data.local.room.RestaurantDatabase
import com.azuka.restofinder.data.local.room.RestaurantDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */
 
@Module
class DatabaseModule(val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): RestaurantDatabase = Room.databaseBuilder(
        context,
        RestaurantDatabase::class.java,
        "RestaurantDatabase.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRestaurantDao(database: RestaurantDatabase): RestaurantDao = database.restaurantDao()
}