package com.dailyapps.data.remote.datasource

import com.dailyapps.data.mapper.ExamMapper
import com.dailyapps.data.remote.service.ExamService
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.ExamListUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Exam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExamRemoteDataSource @Inject constructor(
    private val service: ExamService,
    private val mapper: ExamMapper
){
    suspend fun getExamList(params: ExamListUseCase.Params) : Flow<Resource<List<Exam>>> {
        return mapFromApiResponse(
            result = apiCall {
                service.getExampList(
                    token = params.token,
                    id = params.id,
                    tahunAjaranId =  params.tahunAjaranSemesterId,
                    kelasId = params.kelasId,
                    guruId = params.guruId,
                    matpelId = params.matpelId,
                    jenisUjianId = params.jeniUjianId,
                    isActive =  params.isActive,
                    isGroupBy = params.isGroupBy
                )
            }, mapper
        )
    }
}