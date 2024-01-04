package com.dailyapps.feature.absent.state

import com.dailyapps.entity.CreateAbsent

sealed class CreateAbsentScreenState {
    class Success(val createAbsent: CreateAbsent) : CreateAbsentScreenState()
    class Error(val message: String) : CreateAbsentScreenState()
    object Loading : CreateAbsentScreenState()
    object Empty : CreateAbsentScreenState()
}
