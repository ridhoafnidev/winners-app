package com.dailyapps.data.utils

import com.dailyapps.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Mapper<R,E>{
    fun mapFromApiResponse(type:R):E
}

fun <R,E> mapFromApiResponse(result: Flow<Resource<R>>, mapper: Mapper<R,E>): Flow<Resource<E>> {
    return result.map {
        when(it) {
            is Resource.Success -> Resource.Success(mapper.mapFromApiResponse(it.data))
            is Resource.Error -> Resource.Error(it.msg)
            is Resource.Loading -> Resource.Loading
        }
    }
}