package com.azuka.restofinder.data.remote

import android.util.Log
import com.azuka.restofinder.data.remote.network.ApiResponse
import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.azuka.restofinder.data.remote.response.SearchResponse
import com.azuka.restofinder.data.remote.network.AppNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

interface RemoteDataSource {
    fun getAllRestaurants(): Flow<ApiResponse<List<RestaurantResponse>>>
}

class RemoteDataSourceImpl(private val networkService: AppNetworkService) : RemoteDataSource {

    override fun getAllRestaurants(): Flow<ApiResponse<List<RestaurantResponse>>> {
        return flow {
            try {
                val response = networkService.searchRestaurant(
                    userKey = "1adeb00ef4fefb9a09daf95048515fac",
                    query = "beer"
                ).apply {
                    if (isSuccessful) {
                        val responseBody = body() as SearchResponse
                        emit(ApiResponse.Success(responseBody.restaurants))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
//                val responseBody = response.body() as? SearchResponse
//                val dataArray = responseBody?.restaurants
//                if (!dataArray.isNullOrEmpty()) {
//                    emit(ApiResponse.Success(responseBody.restaurants))
//                } else {
//                    emit(ApiResponse.Empty)
//                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}