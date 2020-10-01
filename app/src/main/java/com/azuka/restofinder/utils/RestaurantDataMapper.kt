package com.azuka.restofinder.utils

import com.azuka.base.utils.Mapper
import com.azuka.restofinder.data.local.entity.RestaurantEntity
import com.azuka.restofinder.data.local.entity.UserRatingEntity
import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.model.UserRating


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

object RestaurantDataMapper : Mapper<RestaurantEntity, Restaurant, RestaurantResponse>() {
    override fun mapEntityToDomain(entity: RestaurantEntity): Restaurant = with(entity) {
        Restaurant(
            id = id,
            name = name,
            userRating = with(userRating) {
                UserRating(
                    votes = votes,
                    rating = rating,
                    ratingColor = ratingColor,
                    ratingText = ratingText
                )
            },
            url = url,
            priceRange = priceRange,
            currency = currency,
            averageCostForTwo = averageCostForTwo,
            isFavorite = isFavorite
        )
    }

    override fun mapDomainToEntity(dto: Restaurant): RestaurantEntity = with(dto) {
        RestaurantEntity(
            id = id,
            isFavorite = isFavorite,
            averageCostForTwo = averageCostForTwo,
            currency = currency,
            priceRange = priceRange,
            url = url,
            userRating = with(userRating) {
                UserRatingEntity(
                    votes = votes,
                    ratingText = ratingText,
                    ratingColor = ratingColor,
                    rating = rating
                )
            },
            name = name,
            isSearchResult = false
        )
    }

    override fun mapResponseToEntity(response: RestaurantResponse): RestaurantEntity =
        with(response.restaurant) {
            RestaurantEntity(
                id = id,
                isFavorite = false,
                averageCostForTwo = averageCostForTwo,
                currency = currency,
                priceRange = priceRange,
                url = url,
                userRating = with(userRating) {
                    UserRatingEntity(
                        votes = votes,
                        ratingText = ratingText,
                        ratingColor = ratingColor,
                        rating = rating
                    )
                },
                name = name,
                isSearchResult = true
            )
        }
}