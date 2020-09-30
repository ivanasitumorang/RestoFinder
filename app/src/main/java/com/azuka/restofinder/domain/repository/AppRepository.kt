package com.azuka.restofinder.domain.repository

import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

interface AppRepository {

    fun searchRestaurant(query: String): Flow<Resource<List<Restaurant>>>
    fun getFavoriteRestaurants(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean)
}