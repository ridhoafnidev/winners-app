package com.dailyapps.data.remote

import com.google.gson.JsonParser
import com.dailyapps.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Throwable) {
                Resource.Error(errorMessage(e))
            }
        }
    }

    fun errorMessage(throwable: Throwable): String {
        return when (throwable) {
            is SocketTimeoutException -> "Ups, terjadi timeout, coba lagi"
            is IOException -> "Ups, ada masalah pada internet kamu"
            is HttpException -> {
                try {
                    val errorJsonString = throwable.response()?.errorBody()?.toString()
                    val errorMessage =
                        JsonParser().parse(errorJsonString).asJsonObject["message"].asString
                    errorMessage.ifEmpty { "Ups! ada yang tidak beres. coba lagi" }
                } catch (e: Exception) {
                    "Ups, error tidak diketahu. coba lagi"
                }
            }

            is UnknownHostException -> "Ups, ada masalah pada internet kamu"
            else -> "Ups, ada yang tidak beres. coba lagi"
        }
    }

}