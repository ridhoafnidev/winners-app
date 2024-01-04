package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IAbsentRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Absent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AbsentUseCase @Inject constructor(
    private val repository: IAbsentRepository
): ApiUseCaseParams<AbsentUseCase.Params, Absent> {

    override suspend fun execute(params: Params): Flow<Resource<Absent>> =
        repository.getAbsentBySiswaId(params)

    data class Params(val siswaId: Int, val token: String, val tahunAjaranSemesterId: Int)
}