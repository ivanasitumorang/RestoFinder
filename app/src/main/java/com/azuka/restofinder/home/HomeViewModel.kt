package com.azuka.restofinder.home

import android.util.Log
import androidx.lifecycle.*
import com.azuka.base.external.CoroutineContextProvider
import com.azuka.restofinder.data.Resource
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.usecase.HomeUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */


class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _searchResult = MediatorLiveData<List<Restaurant>>()
//    val searchResult: LiveData<List<Restaurant>> = _searchResult
    val searchResult = homeUseCase.searchRestaurant("haha").asLiveData()

    //    val restaurants = homeUseCase.getAllRestaurants().asLiveData()
//    fun searchRestaurant(query: String) {
//        _searchResult.value = Resource.Loading()
////        viewModelScope.launch {
//            _searchResult = homeUseCase.searchRestaurant(query).asLiveData(viewModelScope.coroutineContext)
////        }
//    }

    fun searchRestaurant(query: String) {
        var searchRestaurantDataSource: LiveData<Resource<List<Restaurant>>> = MutableLiveData()
        viewModelScope.launch(coroutineContextProvider.mainThreadDispatcher()) {
            withContext(coroutineContextProvider.backgroundDispatcher()) {
                searchRestaurantDataSource = homeUseCase.searchRestaurant(query).asLiveData()
            }
        }

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