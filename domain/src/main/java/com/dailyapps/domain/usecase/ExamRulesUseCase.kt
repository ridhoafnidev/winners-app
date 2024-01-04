package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IExamQuestionRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ExamRules
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamRulesUseCase @Inject constructor(
    private val repository: IExamQuestionRepository
) : ApiUseCaseParams<ExamRulesUseCase.Params, List<ExamRules>> {

    override suspend fun execute(params: Params): Flow<Resource<List<ExamRules>>> =
        repository.getExamRules(params)

    data class Params(
        val token: String = ""
    )

}