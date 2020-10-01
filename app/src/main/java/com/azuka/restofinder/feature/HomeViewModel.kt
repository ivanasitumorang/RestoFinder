package com.azuka.restofinder.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.azuka.base.presentation.BaseViewModel
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.HomeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val _searchResult = MediatorLiveData<List<Restaurant>>()
    val searchResult: LiveData<List<Restaurant>> = _searchResult

    private val _isFavorite = MediatorLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun checkIfFavoriteRestaurant(restaurantId: String?) {
        if (restaurantId == null) {
            _isFavorite.value = false
        } else {
            val isFavoriteRestaurantDataSource =
                homeUseCase.checkIfFavoriteRestaurant(restaurantId).asLiveData()
            _isFavorite.addSource(isFavoriteRestaurantDataSource) { state ->
                _isFavorite.value = state
            }
        }
    }

    fun saveToFavorite(restaurant: Restaurant) {
        // todo : save ke favorite
        _loadingHandler.value = true
        viewModelScope.launch {
            delay(1500)
            _isFavorite.postValue(true)
            _loadingHandler.postValue(false)
        }
    }

    fun removeFromFavorite(restaurant: Restaurant) {
        // todo : remove from favorite
        _loadingHandler.value = true
        viewModelScope.launch {
            delay(1500)
            _isFavorite.postValue(false)
            _loadingHandler.postValue(false)
        }
    }

    fun searchRestaurant(query: String) {
        val searchRestaurantDataSource = homeUseCase.searchRestaurant(query).asLiveData()
        _loadingHandler.value = true
        _searchResult.addSource(searchRestaurantDataSource) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _loadingHandler.value = true
                }
                is Resource.Success -> {
                    _searchResult.value = resource.data
                    _loadingHandler.value = false
                }
                is Resource.Error -> {
                    _loadingHandler.value = false
                }
            }
        }
    }
}