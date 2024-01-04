package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.AbsentRemoteDataSource
import com.dailyapps.domain.repository.IAbsentRepository
import com.dailyapps.domain.usecase.AbsentUseCase
import com.dailyapps.domain.usecase.CreateAbsentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Absent
import com.dailyapps.entity.CreateAbsent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AbsentRepository @Inject  constructor(
    private val absentRemoteDataSource: AbsentRemoteDataSource
): IAbsentRepository {
    override suspend fun getAbsentBySiswaId(params: AbsentUseCase.Params): Flow<Resource<Absent>> =
        absentRemoteDataSource.getAbsentById(params)

    override suspend fun createAbsent(params: CreateAbsentUseCase.Params): Flow<Resource<CreateAbsent>> =
        absentRemoteDataSource.createAbsent(params)
}