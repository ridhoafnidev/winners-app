package com.dailyapps.data.remote.datasource

import com.dailyapps.data.mapper.ScoreMapper
import com.dailyapps.data.remote.service.ScoreService
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.ScoreUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoreRemoteDataSource @Inject constructor(
    private val scoreService: ScoreService,
    private val scoreMapper: ScoreMapper
) {
    suspend fun getScoreStudent(params: ScoreUseCase.Params): Flow<Resource<List<Score>>> {
        return mapFromApiResponse(
            result = apiCall {
                scoreService.getScoreStudent(
                    token = params.token,
                    id = params.id,
                    siswaId = params.siswaId,
                    tahunAjaranId = params.tahunAjaranSemesterId,
                    namaNilaiId = params.namaNilaiId,
                    jenisNilaiId = params.jenisNilaiId,
                    kelasId = params.kelasId,
                    matpelId = params.matpelId,
                    isOrder = params.isOrder
                )
            }, scoreMapper
        )
    }
}