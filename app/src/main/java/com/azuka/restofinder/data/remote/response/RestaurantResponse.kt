package com.azuka.restofinder.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RestaurantResponse(
    val restaurant: RestaurantObjectResponse
)

@Keep
data class RestaurantObjectResponse(
    val id: String,
    val name: String,
    val url: String,
    val currency: String,
    val cuisines: String,
    @SerializedName("average_cost_for_two") val averageCostForTwo: String,
    @SerializedName("price_range") val priceRange: String,
    @SerializedName("featured_image") val featuredImage: String,
    @SerializedName("user_rating") val userRating: UserRatingResponse
)

@Keep
data class UserRatingResponse(
    val votes: String,
    @SerializedName("aggregate_rating") val rating: String,
    @SerializedName("rating_text") val ratingText: String,
    @SerializedName("rating_color") val ratingColor: String
)