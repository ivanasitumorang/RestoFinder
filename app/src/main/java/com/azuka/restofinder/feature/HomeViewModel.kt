package com.azuka.restofinder.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import com.azuka.base.presentation.BaseViewModel
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.AppUseCase


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(
    private val appUseCase: AppUseCase
) : BaseViewModel() {

    private val _searchResult = MediatorLiveData<List<Restaurant>>()
    val searchResult: LiveData<List<Restaurant>> = _searchResult

    fun searchRestaurant(query: String) {
        val searchRestaurantDataSource = appUseCase.searchRestaurant(query).asLiveData()
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
                    _errorHandler.value = resource.errorData
                }
            }
        }
    }
}