package com.azuka.restofinder.domain.usecase

import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.repository.AppRepository

class HomeUseCaseImpl (private val repository: AppRepository) : HomeUseCase {

    override fun getAllRestaurants() = repository.getAllRestaurants()

    override fun getFavoriteRestaurants() = repository.getFavoriteRestaurants()

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) =
        repository.setFavoriteRestaurant(restaurant, isFavorite)
}