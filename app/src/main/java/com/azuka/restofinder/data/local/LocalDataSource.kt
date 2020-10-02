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
    fun getSearchResultRestaurant(): Flow<List<RestaurantEntity>>
    fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>>
    suspend fun insertRestaurants(restoList: List<RestaurantEntity>)
    fun getFavoriteRestaurantById(restaurantId: String): Flow<List<RestaurantEntity>>
    fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean)
}

class LocalDataSourceImpl(
    private val restaurantDao: RestaurantDao,
    private val coroutineContextProvider: CoroutineContextProvider
) : LocalDataSource {

    override fun getSearchResultRestaurant(): Flow<List<RestaurantEntity>> =
        restaurantDao.getSearchResultRestaurant()

    override fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>> =
        restaurantDao.getFavoriteRestaurants()

    override suspend fun insertRestaurants(restoList: List<RestaurantEntity>) {
        CoroutineScope(coroutineContextProvider.backgroundDispatcher()).launch {
            restaurantDao.clearRestaurants()
            restaurantDao.insertRestaurant(restoList)
        }
    }

    override fun getFavoriteRestaurantById(restaurantId: String): Flow<List<RestaurantEntity>> =
        restaurantDao.getFavoriteRestaurantById(restaurantId)

    override fun setFavoriteRestaurant(resto: RestaurantEntity, isFavorite: Boolean) {
        CoroutineScope(coroutineContextProvider.backgroundDispatcher()).launch {
            resto.isFavorite = isFavorite
            restaurantDao.updateFavoriteRestaurant(resto)
        }
    }
}