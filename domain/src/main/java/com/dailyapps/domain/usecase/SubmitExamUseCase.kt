package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IExamQuestionRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.SubmitExamRequest
import com.dailyapps.entity.SubmitExamResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitExamUseCase @Inject constructor(
    private val repository: IExamQuestionRepository
) : ApiUseCaseParams<SubmitExamUseCase.Params, List<SubmitExamResponse>> {

    override suspend fun execute(params: Params): Flow<Resource<List<SubmitExamResponse>>> =
        repository.submitExam(params)

    data class Params(
        val token: String = "",
        val submitExamRequests: List<SubmitExamRequest> = emptyList()
    )

}