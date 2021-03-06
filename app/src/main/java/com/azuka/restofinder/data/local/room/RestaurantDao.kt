package com.azuka.restofinder.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azuka.restofinder.data.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getSearchResultRestaurant(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurant where isFavorite = 1")
    fun getFavoriteRestaurants(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRestaurant(restaurantEntities: List<RestaurantEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoriteRestaurant(restaurantEntity: RestaurantEntity)

    @Query("DELETE FROM restaurant WHERE isFavorite = 0")
    fun clearRestaurants()

    @Query("SELECT * FROM restaurant WHERE id = :restaurantId AND isFavorite = 1")
    fun getFavoriteRestaurantById(restaurantId: String): Flow<List<RestaurantEntity>>
}