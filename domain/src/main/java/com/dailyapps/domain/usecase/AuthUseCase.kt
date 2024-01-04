package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IAuthRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: IAuthRepository
): ApiUseCaseParams<AuthUseCase.Params, User> {

    override suspend fun execute(params: Params): Flow<Resource<User>> = repository.login(params)

    data class Params(val usernameNisn: String, val password: String)

    data class ChangePasswordParams(val oldPass: String, val newPass: String, val newPassAgain: String)

}

