package com.azuka.restofinder.data

import com.azuka.base.data.ErrorResponse
import com.azuka.restofinder.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

abstract class NetworkBoundResource<ResultType, RequestType> {

    var responseData: RequestType? = null

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    responseData = apiResponse.data
                    if (shouldSaveResult()) saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    try {
                        emit(Resource.Error(apiResponse.errorResponse))
                    } catch (e: Exception) {
                        emit(Resource.Error(ErrorResponse(exception = e)))
                    }

                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun shouldSaveResult(): Boolean

    protected abstract fun getResultFromResponse(): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}