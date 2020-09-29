package com.azuka.restaurantfinder.data.source.remote.response

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