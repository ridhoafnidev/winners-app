package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.ExamRemoteDataSource
import com.dailyapps.domain.repository.IExamRepository
import com.dailyapps.domain.usecase.ExamListUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Exam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamRepository @Inject constructor(
    private val remoteDataSource: ExamRemoteDataSource
) : IExamRepository {
    override suspend fun getExamList(params: ExamListUseCase.Params): Flow<Resource<List<Exam>>> =
        remoteDataSource.getExamList(params)
}