package com.azuka.restofinder.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

@Keep
data class SearchResponse(
    @SerializedName("results_shown")
    val resultShown: String,
    val restaurants: List<RestaurantResponse>
)