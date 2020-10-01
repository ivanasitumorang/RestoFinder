package com.azuka.restofinder.data.remote.response

import com.azuka.base.data.BaseResponse
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    val restaurant: RestaurantObjectResponse
) : BaseResponse

data class RestaurantObjectResponse(
    val id: String,
    val name: String,
    val url: String,
    val currency: String,
    @SerializedName("average_cost_for_two") val averageCostForTwo: String,
    @SerializedName("price_range") val priceRange: String,
    @SerializedName("user_rating") val userRating: UserRatingResponse
) : BaseResponse

data class UserRatingResponse(
    val votes: String,
    @SerializedName("aggregate_rating") val rating: String,
    @SerializedName("rating_text") val ratingText: String,
    @SerializedName("rating_color") val ratingColor: String
) : BaseResponse