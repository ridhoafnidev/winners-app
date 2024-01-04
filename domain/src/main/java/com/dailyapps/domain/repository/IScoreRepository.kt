package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.ScoreUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Score
import kotlinx.coroutines.flow.Flow

interface IScoreRepository {
    suspend fun getScoreStudent(params: ScoreUseCase.Params): Flow<Resource<List<Score>>>
}