package com.azuka.restofinder.utils


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

object Screen {
    private const val domain = "com.azuka.restofinder"

    val detailRestaurant = Class.forName("$domain.favorite.feature.detail.DetailActivity")
    val favoriteList = Class.forName("$domain.favorite.feature.favoritelist.FavoriteListActivity")
}