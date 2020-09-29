package com.azuka.restaurantfinder.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azuka.restofinder.data.local.room.RestaurantDao


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

@Database(entities = [], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

//    companion object {
//        @Volatile
//        private var INSTANCE: RestaurantDatabase? = null
//
//        fun getInstance(context: Context): RestaurantDatabase =
//            INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    RestaurantDatabase::class.java,
//                    "RestaurantDatabase.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//    }
}