package com.azuka.restofinder.data.remote.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */
 
interface AppNetworkService {
    @GET("search")
    suspend fun searchRestaurant(
        @Header("user-key") userKey: String,
        @Query("q") query: String
    ): Response<Any>
}