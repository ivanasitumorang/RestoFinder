package com.azuka.restofinder.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azuka.base.external.CoroutineContextProvider
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.HomeUseCase


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _searchResult = MediatorLiveData<List<Restaurant>>()
    val searchResult: LiveData<List<Restaurant>> = _searchResult

    fun searchRestaurant(query: String) {
        val searchRestaurantDataSource = homeUseCase.searchRestaurant(query).asLiveData()
        _searchResult.addSource(searchRestaurantDataSource) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.i("Hasil", "loading")
                }
                is Resource.Success -> {
                    Log.i("Hasil", "success ${resource.data}")
                    _searchResult.value = resource.data
                }
                is Resource.Error -> {
                    Log.i("Hasil", "error")
                }
            }
        }
    }
}