package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IAbsentRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.CreateAbsent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateAbsentUseCase @Inject constructor(
    private val repository: IAbsentRepository
) : ApiUseCaseParams<CreateAbsentUseCase.Params, CreateAbsent> {

    override suspend fun execute(params: Params): Flow<Resource<CreateAbsent>> =
        repository.createAbsent(params)

    data class Params(
        val token: String,
        val siswaId: Int,
        val jamMasuk: String,
        val tahunAjaranSemesterId: Int,
        val tanggalAbsensi: String,
        val jenisAbsensiId: Int,
        val latitude: String,
        val longitude: String,
        val foto: String
    )
}