package com.azuka.restofinder.data

import com.azuka.restofinder.data.local.LocalDataSource
import com.azuka.restofinder.data.remote.RemoteDataSource
import com.azuka.restofinder.data.remote.network.ApiResponse
import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.repository.AppRepository
import com.azuka.restofinder.utils.RestaurantDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by ivanaazuka on 24/09/20.
 * Android Engineer
 */

class AppRepositoryImpl (
    private val localData: LocalDataSource,
    private val remoteData: RemoteDataSource
) : AppRepository {

    override fun searchRestaurant(query: String): Flow<Resource<List<Restaurant>>> =
        object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localData.getSearchResultRestaurant().map {
                    RestaurantDataMapper.mapEntitiesToDomains(it)
                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                remoteData.searchRestaurant(query)

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val restaurantList = RestaurantDataMapper.mapResponsesToEntities(data)
                localData.insertRestaurants(restaurantList)
            }

        }.asFlow()

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {
        return localData.getFavoriteRestaurants().map {
            RestaurantDataMapper.mapEntitiesToDomains(it)
        }
    }

    override fun getFavoriteRestaurantById(restaurantId: String): Flow<List<Restaurant>> {
        return localData.getFavoriteRestaurantById(restaurantId).map {
            RestaurantDataMapper.mapEntitiesToDomains(it)
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) {
        val restoEntity = RestaurantDataMapper.mapDomainToEntity(restaurant)
//        appExecutor.diskIO().execute { localData.setFavoriteRestaurant(restoEntity, isFavorite) }
    }

}