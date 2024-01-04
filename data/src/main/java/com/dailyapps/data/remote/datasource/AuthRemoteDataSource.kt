package com.dailyapps.data.remote.datasource

import com.dailyapps.data.mapper.AuthMapper
import com.dailyapps.data.mapper.ChangePasswordMapper
import com.dailyapps.data.remote.service.AuthService
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.AuthUseCase
import com.dailyapps.domain.usecase.ChangePasswordUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangePassword
import com.dailyapps.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService,
    private val authMapper: AuthMapper,
    private val changePasswordMapper: ChangePasswordMapper,
) {
    suspend fun login(params: AuthUseCase.Params): Flow<Resource<User>> {
        return mapFromApiResponse(
            result = apiCall(true) {
                authService.login(params.usernameNisn, params.password)
            }, authMapper
        )
    }

    suspend fun changePassword(params: ChangePasswordUseCase.Params): Flow<Resource<ChangePassword>> {
        return mapFromApiResponse(
            result = apiCall {
                authService.changePassword(params.token, params.oldPass, params.newPass, params.newPassAgain)
            }, changePasswordMapper
        )
    }
}