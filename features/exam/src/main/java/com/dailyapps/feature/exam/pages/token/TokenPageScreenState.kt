package com.dailyapps.feature.exam.pages.token

sealed class TokenPageScreenState<out T> {
    class Success<T>(val data: T): TokenPageScreenState<T>()
    class Error(val message: String): TokenPageScreenState<Nothing>()
    object Loading: TokenPageScreenState<Nothing>()
}