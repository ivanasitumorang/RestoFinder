package com.azuka.restofinder.feature.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import com.azuka.base.presentation.BaseViewModel
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.HomeUseCase


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val _searchResult = MediatorLiveData<List<Restaurant>>()
    val searchResult: LiveData<List<Restaurant>> = _searchResult

    fun searchRestaurant(query: String) {
        val searchRestaurantDataSource = homeUseCase.searchRestaurant(query).asLiveData()
        _loadingHandler.value = true
        _searchResult.addSource(searchRestaurantDataSource) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.i("Hasil", "loading")
                    _loadingHandler.value = true
                }
                is Resource.Success -> {
                    Log.i("Hasil", "success ${resource.data}")
                    _searchResult.value = resource.data
                    _loadingHandler.value = false
                }
                is Resource.Error -> {
                    Log.i("Hasil", "error")
                    _loadingHandler.value = false
                }
            }
        }
    }
}