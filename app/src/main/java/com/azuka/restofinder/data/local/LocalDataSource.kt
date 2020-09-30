package com.azuka.restofinder.data.local

import com.azuka.base.external.CoroutineContextProvider
import com.azuka.restofinder.data.local.entity.RestaurantEntity
import com.azuka.restofinder.data.local.room.RestaurantDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


/**
 * Created by ivanaazuka on 21/09/20.
 * Android Engineer
 */

interface LocalDataSource {
    fun searchRestaurant(query: String): Flow<List<RestaurantEntity>>
    fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>>
    suspend fun insertRestaurants(restoList: List<RestaurantEntity>)
    fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean)
}

class LocalDataSourceImpl(
    private val restaurantDao: RestaurantDao,
    private val coroutineContextProvider: CoroutineContextProvider
) : LocalDataSource {

    override fun searchRestaurant(query: String): Flow<List<RestaurantEntity>> =
        restaurantDao.searchRestaurant(query)

    override fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>> =
        restaurantDao.getFavoriteRestaurants()

    override suspend fun insertRestaurants(restoList: List<RestaurantEntity>) {
        CoroutineScope(coroutineContextProvider.backgroundDispatcher()).launch {
            restaurantDao.insertRestaurant(restoList)
        }
    }

    override fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean) {
        resto.isFavorite = isFavorite
        restaurantDao.updateFavoriteRestaurant(resto)
    }
}