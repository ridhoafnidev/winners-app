package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.ExamListUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Exam
import kotlinx.coroutines.flow.Flow

interface IExamRepository {
    suspend fun getExamList(params: ExamListUseCase.Params): Flow<Resource<List<Exam>>>
}