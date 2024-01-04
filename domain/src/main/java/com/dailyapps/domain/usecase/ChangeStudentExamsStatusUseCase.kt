package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IExamQuestionRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeStudentExamsStatusUseCase @Inject constructor(
    private val repository: IExamQuestionRepository
) : ApiUseCaseParams<ChangeStudentExamsStatusUseCase.Params, ChangeStudentExamsStateResponse> {

    override suspend fun execute(params: Params): Flow<Resource<ChangeStudentExamsStateResponse>> =
        repository.changeStudentExamsStatus(params)

    data class Params(
        val token: String = "",
        val studentId: Int = 0,
        val examId: Int = 0,
        val status: Int = 0
    )

}