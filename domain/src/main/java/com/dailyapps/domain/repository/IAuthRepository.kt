package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.AuthUseCase
import com.dailyapps.domain.usecase.ChangePasswordUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangePassword
import com.dailyapps.entity.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun login(params: AuthUseCase.Params):Flow<Resource<User>>
    suspend fun changePassword(params: ChangePasswordUseCase.Params):Flow<Resource<ChangePassword>>
}