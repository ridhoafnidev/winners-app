package com.dailyapps.domain.repository

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

interface IExamQuestionRepository {

    suspend fun getExamQuestions(params: ExamQuestionUseCase.Params): Flow<Resource<List<ExamQuestion>>>

    suspend fun submitExam(params: SubmitExamUseCase.Params): Flow<Resource<List<SubmitExamResponse>>>

    suspend fun getExamRules(params: ExamRulesUseCase.Params): Flow<Resource<List<ExamRules>>>

    suspend fun changeStudentExamsStatus(params: ChangeStudentExamsStatusUseCase.Params): Flow<Resource<ChangeStudentExamsStateResponse>>

}