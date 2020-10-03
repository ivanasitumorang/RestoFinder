package com.azuka.restofinder.domain.usecase

import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.repository.AppRepository
import kotlinx.coroutines.flow.*

class AppUseCaseImpl(private val repository: AppRepository) : AppUseCase {

    override fun searchRestaurant(query: String) = repository.searchRestaurant(query)

    override fun getFavoriteRestaurants() = repository.getFavoriteRestaurants()

    override fun checkIfFavoriteRestaurant(restaurantId: String): Flow<Boolean> {
        val result = repository.getFavoriteRestaurantById(restaurantId)
        return result.map {
            it.find { restaurant -> restaurant.id == restaurantId } != null
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) =
        repository.setFavoriteRestaurant(restaurant, isFavorite)
}