package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IScoreRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreUseCase @Inject constructor(
    private val repository: IScoreRepository
) : ApiUseCaseParams<ScoreUseCase.Params, List<Score>> {
    override suspend fun execute(params: Params): Flow<Resource<List<Score>>> =
        repository.getScoreStudent(params)

    data class Params(
        val token: String,
        val id: Int,
        val siswaId: Int,
        val tahunAjaranSemesterId: Int,
        val namaNilaiId: Int,
        val jenisNilaiId: Int,
        val kelasId: Int,
        val matpelId: Int,
        val isOrder: Int
    )

}