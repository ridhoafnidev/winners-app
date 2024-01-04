package com.dailyapps.data.utils

import com.dailyapps.domain.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Response<RequestType>,
    crossinline saveFetchResult: suspend (Response<RequestType>) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Result.Loading(true))
        try {
            delay(1000)
            saveFetchResult(fetch())
            query().map { Result.Success(data = it) }
        } catch (throwable: Throwable) {
            query().map { Result.Error<ResultType>(message(throwable)) }
        }
    } else {
        query().map { Result.Success(data = it) }
    }

    emitAll(flow)
}