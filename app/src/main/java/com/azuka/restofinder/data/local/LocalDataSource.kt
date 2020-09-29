package com.azuka.restofinder.data.local

import com.azuka.restofinder.data.local.entity.RestaurantEntity
import com.azuka.restofinder.data.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow


/**
 * Created by ivanaazuka on 21/09/20.
 * Android Engineer
 */

interface LocalDataSource {
    fun getAllRestaurants(): Flow<List<RestaurantEntity>>
    fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>>
    fun insertRestaurants(restoList: List<RestaurantEntity>)
    fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean)
}

class LocalDataSourceImpl(private val restaurantDao: RestaurantDao) : LocalDataSource {

    override fun getAllRestaurants(): Flow<List<RestaurantEntity>> =
        restaurantDao.getAllRestaurants()

    override fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>> =
        restaurantDao.getFavoriteRestaurants()

    override fun insertRestaurants(restoList: List<RestaurantEntity>) =
        restaurantDao.insertRestaurant(restoList)

    override fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean) {
        resto.isFavorite = isFavorite
        restaurantDao.updateFavoriteRestaurant(resto)
    }
}