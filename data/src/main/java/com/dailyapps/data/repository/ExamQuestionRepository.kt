package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.ExamQuestionRemoteDataSource
import com.dailyapps.domain.repository.IExamQuestionRepository
import com.dailyapps.domain.usecase.ExamQuestionUseCase
import com.dailyapps.domain.usecase.ExamRulesUseCase
import com.dailyapps.domain.usecase.SubmitExamUseCase
import com.dailyapps.domain.usecase.ChangeStudentExamsStatusUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ExamQuestion
import com.dailyapps.entity.ExamRules
import com.dailyapps.entity.SubmitExamResponse
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExamQuestionRepository @Inject constructor(
    private val remoteDataSource: ExamQuestionRemoteDataSource
) : IExamQuestionRepository {

    override suspend fun getExamQuestions(params: ExamQuestionUseCase.Params): Flow<Resource<List<ExamQuestion>>> =
        remoteDataSource.getExamQuestion(params)

    override suspend fun submitExam(params: SubmitExamUseCase.Params): Flow<Resource<List<SubmitExamResponse>>> =
        remoteDataSource.submitExam(params)

    override suspend fun getExamRules(params: ExamRulesUseCase.Params): Flow<Resource<List<ExamRules>>> =
        remoteDataSource.getExamRules(params)

    override suspend fun changeStudentExamsStatus(params: ChangeStudentExamsStatusUseCase.Params): Flow<Resource<ChangeStudentExamsStateResponse>> =
        remoteDataSource.changeStudentExamsStatus(params)
}