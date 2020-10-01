package com.azuka.restofinder.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

@Parcelize
data class UserRating(
    val votes: String,
    val rating: String,
    val ratingText: String,
    val ratingColor: String
) : Parcelable