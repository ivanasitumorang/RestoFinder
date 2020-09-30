package com.azuka.restofinder.data.remote.response

data class RestaurantResponse(
    val restaurant: RestaurantObjectResponse
)

data class RestaurantObjectResponse(
    val id: String,
    val name: String
)