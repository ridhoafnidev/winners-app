package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.AuthRemoteDataSource
import com.dailyapps.domain.repository.IAuthRepository
import com.dailyapps.domain.usecase.AuthUseCase
import com.dailyapps.domain.usecase.ChangePasswordUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangePassword
import com.dailyapps.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
): IAuthRepository {
    override suspend fun login(params: AuthUseCase.Params): Flow<Resource<User>> =
        authRemoteDataSource.login(params)

    override suspend fun changePassword(params: ChangePasswordUseCase.Params): Flow<Resource<ChangePassword>> =
        authRemoteDataSource.changePassword(params)
}