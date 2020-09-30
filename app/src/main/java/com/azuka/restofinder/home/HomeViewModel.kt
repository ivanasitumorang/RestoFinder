package com.azuka.restofinder.home

import androidx.lifecycle.*
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.HomeUseCase
import kotlinx.coroutines.launch


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _searchResult = MutableLiveData<Resource<List<Restaurant>>>()
    val searchResult: LiveData<Resource<List<Restaurant>>> = _searchResult

    //    val restaurants = homeUseCase.getAllRestaurants().asLiveData()
    fun searchRestaurant(query: String) {
        _searchResult.value = Resource.Loading()
        viewModelScope.launch {
            _searchResult.value = homeUseCase.searchRestaurant(query).asLiveData().value
        }
    }
}