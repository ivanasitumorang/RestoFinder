package com.azuka.restofinder.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    val name: String,
    val url: String,
    val currency: String,
    val averageCostForTwo: String,
    val priceRange: String,
    @Embedded
    val userRating: UserRatingEntity,
    var isFavorite: Boolean
)