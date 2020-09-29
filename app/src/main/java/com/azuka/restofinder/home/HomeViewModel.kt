package com.azuka.restofinder.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azuka.restofinder.domain.usecase.HomeUseCase


/**
 * Created by ivanaazuka on 29/09/20.
 * Android Engineer
 */
 

class HomeViewModel (homeUseCase: HomeUseCase) : ViewModel() {
    val restaurants = homeUseCase.getAllRestaurants().asLiveData()
}