package com.azuka.restofinder.domain.model

import android.os.Parcelable
import com.azuka.restofinder.data.remote.response.UserRatingResponse
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val url: String,
    val currency: String,
    val averageCostForTwo: String,
    val priceRange: String,
    val featuredImage: String,
    val cuisines: String,
    val userRating: UserRating,
    val isFavorite: Boolean
) : Parcelable