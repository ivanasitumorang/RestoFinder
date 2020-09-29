package com.azuka.restofinder.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "restoId")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean

)