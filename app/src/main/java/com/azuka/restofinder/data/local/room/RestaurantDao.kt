package com.azuka.restofinder.data.local.room

import androidx.room.*
import com.azuka.restofinder.data.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurants(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurant where isFavorite = 1")
    fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurantEntities: List<RestaurantEntity>)

    @Update
    fun updateFavoriteRestaurant(restaurantEntity: RestaurantEntity)
}