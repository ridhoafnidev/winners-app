package com.dailyapps.feature.exam.pages.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailyapps.domain.usecase.ChangeStudentExamsStatusUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenPageViewModel @Inject constructor(
    private val changeStudentExamsStatusUseCase: ChangeStudentExamsStatusUseCase
) : ViewModel() {

    private val _changeStudentExamsStatusResponse = MutableStateFlow<TokenPageScreenState<ChangeStudentExamsStateResponse>>(TokenPageScreenState.Loading)
    val changeStudentExamsStatusResponse get() = _changeStudentExamsStatusResponse.asStateFlow()

    private val _token = MutableStateFlow("")
    val token get() = _token.asStateFlow()

    private val _buttonSubmitLoadingState = MutableStateFlow(false)
    val buttonSubmitLoadingState get() = _buttonSubmitLoadingState.asStateFlow()

    private val _tokenInvalid = MutableStateFlow(false)
    val tokenInvalid get() = _tokenInvalid.asStateFlow()

    val buttonSubmitEnableState get() = _token.map {
        it.isNotEmpty()
    }


    fun changeStudentExamsStateToAttempt(token: String, studentId: Int, examId: Int) {
        viewModelScope.launch {
            val suspendStudentExamsParams = ChangeStudentExamsStatusUseCase.Params(
                token, studentId, examId, 2
            )
            changeStudentExamsStatusUseCase.execute(suspendStudentExamsParams).collect { result ->
                when (result) {
                    Resource.Loading -> {
                        _buttonSubmitLoadingState.value = true
                        _changeStudentExamsStatusResponse.value = TokenPageScreenState.Loading
                    }
                    is Resource.Error -> {
                        _buttonSubmitLoadingState.value = false
                        _changeStudentExamsStatusResponse.value = TokenPageScreenState.Error(result.msg)
                    }
                    is Resource.Success -> {
                        _buttonSubmitLoadingState.value = false
                        _changeStudentExamsStatusResponse.value = TokenPageScreenState.Success(result.data)
                    }
                }
            }
        }
    }

    fun updateToken(token: String) {
        viewModelScope.launch {
            _token.value = token
        }
    }

    fun updateTokenInvalid(state: Boolean) {
        viewModelScope.launch {
            _tokenInvalid.value = state
        }
    }

    fun updateButtonSubmitLoadingState(state: Boolean) {
        viewModelScope.launch {
            _buttonSubmitLoadingState.value = state
        }
    }

}