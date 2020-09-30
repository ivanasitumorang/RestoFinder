package com.azuka.restofinder.data

import com.azuka.restofinder.data.local.LocalDataSource
import com.azuka.restofinder.data.remote.RemoteDataSource
import com.azuka.restofinder.data.remote.network.ApiResponse
import com.azuka.restofinder.data.remote.response.RestaurantResponse
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.domain.repository.AppRepository
import com.azuka.restofinder.utils.DataMapper
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

    override fun getAllRestaurants(): Flow<Resource<List<Restaurant>>> =
        object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localData.getAllRestaurants().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                remoteData.getAllRestaurants()

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val restaurantList = DataMapper.mapResponsesToEntities(data)
                localData.insertRestaurants(restaurantList)
            }

        }.asFlow()

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {
        return localData.getFavoriteRestaurants().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) {
        val restoEntity = DataMapper.mapDomainToEntity(restaurant)
//        appExecutor.diskIO().execute { localData.setFavoriteRestaurant(restoEntity, isFavorite) }
    }

}