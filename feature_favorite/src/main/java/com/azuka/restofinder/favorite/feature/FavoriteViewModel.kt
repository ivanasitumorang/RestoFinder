package com.azuka.restofinder.favorite.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import com.azuka.base.presentation.BaseViewModel
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.AppUseCase


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

class FavoriteViewModel(private val appUseCase: AppUseCase) : BaseViewModel() {

    private val _favoriteRestaurants = MediatorLiveData<List<Restaurant>>()
    val favoriteRestaurants: LiveData<List<Restaurant>> = _favoriteRestaurants

    private val _isFavorite = MediatorLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getFavoriteRestaurants() {
        val favoriteRestaurantsDataSource = appUseCase.getFavoriteRestaurants().asLiveData()
        _loadingHandler.value = true
        _favoriteRestaurants.addSource(favoriteRestaurantsDataSource) { restaurants ->
            _favoriteRestaurants.value = restaurants
            _loadingHandler.postValue(false)
        }
    }

    fun checkIfFavoriteRestaurant(restaurantId: String?) {
        if (restaurantId == null) {
            _isFavorite.value = false
        } else {
            _loadingHandler.value = true
            val isFavoriteRestaurantDataSource =
                appUseCase.checkIfFavoriteRestaurant(restaurantId).asLiveData()
            _isFavorite.addSource(isFavoriteRestaurantDataSource) { state ->
                _isFavorite.value = state
                _loadingHandler.postValue(false)
            }
        }
    }

    fun saveToFavorite(restaurant: Restaurant) {
        appUseCase.setFavoriteRestaurant(restaurant, true)
    }

    fun removeFromFavorite(restaurant: Restaurant) {
        appUseCase.setFavoriteRestaurant(restaurant, false)
    }
}