package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IAuthRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangePassword
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: IAuthRepository
): ApiUseCaseParams<ChangePasswordUseCase.Params, ChangePassword> {

    override suspend fun execute(params: Params): Flow<Resource<ChangePassword>> = repository.changePassword(params)

    data class Params(val token: String, val oldPass: String, val newPass: String, val newPassAgain: String)

}

