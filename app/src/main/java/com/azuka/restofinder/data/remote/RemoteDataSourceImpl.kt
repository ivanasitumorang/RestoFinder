package com.azuka.restofinder.data.remote

import android.util.Log
import com.azuka.restofinder.data.remote.network.ApiResponse
import com.azuka.restofinder.data.remote.network.AppNetworkService
import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.azuka.restofinder.data.remote.response.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

interface RemoteDataSource {
    fun searchRestaurant(query: String): Flow<ApiResponse<List<RestaurantResponse>>>
}

class RemoteDataSourceImpl(private val networkService: AppNetworkService) : RemoteDataSource {

    override fun searchRestaurant(query: String): Flow<ApiResponse<List<RestaurantResponse>>> {
        return flow {
            try {
                networkService.searchRestaurant(
                    userKey = "1adeb00ef4fefb9a09daf95048515fac",
                    query = query
                ).apply {
                    if (isSuccessful) {
                        val responseBody = body() as SearchResponse
                        emit(ApiResponse.Success(responseBody.restaurants))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}