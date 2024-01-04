package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.AbsentUseCase
import com.dailyapps.domain.usecase.CreateAbsentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Absent
import com.dailyapps.entity.CreateAbsent
import kotlinx.coroutines.flow.Flow

interface IAbsentRepository {
    suspend fun getAbsentBySiswaId(params: AbsentUseCase.Params): Flow<Resource<Absent>>
    suspend fun createAbsent(params: CreateAbsentUseCase.Params): Flow<Resource<CreateAbsent>>
}