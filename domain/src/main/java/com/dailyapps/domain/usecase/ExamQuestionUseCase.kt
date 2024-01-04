package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IExamQuestionRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ExamQuestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamQuestionUseCase @Inject constructor(
    private val repository: IExamQuestionRepository
) : ApiUseCaseParams<ExamQuestionUseCase.Params, List<ExamQuestion>> {

    override suspend fun execute(params: Params): Flow<Resource<List<ExamQuestion>>> =
        repository.getExamQuestions(params)

    data class Params(
        val token: String = "",
        val ujianId: Int = 0
    )

}