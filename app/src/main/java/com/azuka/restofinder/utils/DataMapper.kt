package com.azuka.restofinder.utils

import com.azuka.restaurantfinder.data.source.remote.response.RestaurantResponse
import com.azuka.restofinder.data.local.entity.RestaurantEntity
import com.azuka.restofinder.domain.model.Restaurant


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

object DataMapper {
    fun mapEntitiesToDomain(input: List<RestaurantEntity>): List<Restaurant> =
        input.map {
            Restaurant(
                id = it.id,
                name = it.name,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Restaurant) = RestaurantEntity(
        id = input.id,
        name = input.name,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToEntities(input: List<RestaurantResponse>): List<RestaurantEntity> {
        val restaurantList = ArrayList<RestaurantEntity>()
        input.map {
            val resto = RestaurantEntity(
                id = it.id,
                name = it.name,
                isFavorite = false
            )
            restaurantList.add(resto)
        }
        return restaurantList
    }
}