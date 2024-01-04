package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.ScoreRemoteDataSource
import com.dailyapps.domain.repository.IScoreRepository
import com.dailyapps.domain.usecase.ScoreUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepository @Inject constructor(
    private val remoteDataSource: ScoreRemoteDataSource
) : IScoreRepository {
    override suspend fun getScoreStudent(params: ScoreUseCase.Params): Flow<Resource<List<Score>>> =
        remoteDataSource.getScoreStudent(params)
}