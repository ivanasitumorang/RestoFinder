package com.azuka.restofinder.domain.usecase

import com.azuka.restaurantfinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow


/**
 * Created by ivanaazuka on 23/09/20.
 * Android Engineer
 */

interface HomeUseCase {
    fun getAllRestaurants(): Flow<Resource<List<Restaurant>>>
    fun getFavoriteRestaurants(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean)
}