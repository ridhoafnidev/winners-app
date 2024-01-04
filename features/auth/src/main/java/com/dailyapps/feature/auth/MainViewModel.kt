package com.dailyapps.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailyapps.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: UserUseCase
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn

    private val _isSplash = MutableStateFlow(false)
    val isSplash get() = _isSplash

    init {
        setIsPlash(true)
    }

    private fun setIsPlash(boolean: Boolean) {
        viewModelScope.launch {
            delay(2000L)
            _isSplash.value = boolean
        }
    }

    fun getIsLoggedInUser() {
        viewModelScope.launch {
            authUseCase.getIsLoggedIn().collect {
                _isLoggedIn.value = it
            }
        }
    }
}