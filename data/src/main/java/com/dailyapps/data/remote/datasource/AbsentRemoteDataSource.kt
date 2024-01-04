package com.dailyapps.data.remote.datasource

import com.dailyapps.data.mapper.AbsentMapper
import com.dailyapps.data.mapper.CreateAbsentMapper
import com.dailyapps.data.remote.service.AbsentService
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.AbsentUseCase
import com.dailyapps.domain.usecase.CreateAbsentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Absent
import com.dailyapps.entity.CreateAbsent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AbsentRemoteDataSource @Inject constructor(
    private val absentService: AbsentService,
    private val absentMapper: AbsentMapper,
    private val createAbsentMapper: CreateAbsentMapper,
) {
    suspend fun getAbsentById(params: AbsentUseCase.Params): Flow<Resource<Absent>> {
        return mapFromApiResponse(
            result = apiCall {
                absentService.getAbsentById(
                    siswaId = params.siswaId,
                    token = params.token,
                    tahunAjaran = params.tahunAjaranSemesterId
                )
            }, absentMapper
        )
    }

    suspend fun createAbsent(params: CreateAbsentUseCase.Params): Flow<Resource<CreateAbsent>> {
        return mapFromApiResponse(
            result = apiCall {
                absentService.createAbsent(
                    token = params.token,
                    siswaId = params.siswaId,
                    jamMasuk = params.jamMasuk,
                    tahunAjaranSemesterId = params.tahunAjaranSemesterId,
                    tanggalAbsensi = params.tanggalAbsensi,
                    jenisAbsensiId = params.jenisAbsensiId,
                    latitude = params.latitude,
                    longitude = params.longitude,
                    foto = params.foto,
                )
            }, createAbsentMapper
        )
    }
}