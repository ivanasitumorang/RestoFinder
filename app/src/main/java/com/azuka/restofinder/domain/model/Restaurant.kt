package com.azuka.restofinder.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val isFavorite: Boolean
) : Parcelable