package com.azuka.restofinder.data.remote.response

import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */
 
data class SearchResponse(
    @SerializedName("results_shown")
    val resultShown: String,
    val restaurants: List<RestaurantResponse>
)