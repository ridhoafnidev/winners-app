package com.dailyapps.domain.utils

sealed class Resource<out T> {
  class Success<T>(val data: T): Resource<T>()
  class Error(val msg: String = ""): Resource<Nothing>()
  object Loading: Resource<Nothing>()
}
