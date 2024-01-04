package com.dailyapps.feature.exam

import androidx.lifecycle.ViewModel
import com.dailyapps.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {
    protected suspend inline fun <reified T> Flow<T>.runFlow(state: MutableStateFlow<T>) {
        val clazz = T::class.java
        if (clazz.isAssignableFrom(Resource::class.java)) {
            state.value= Resource.Loading as T
        }
        collect {
            state.value= it
        }
    }
}