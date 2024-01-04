package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IExamRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Exam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamListUseCase @Inject constructor(
    private val repository: IExamRepository
): ApiUseCaseParams<ExamListUseCase.Params, List<Exam>> {
    override suspend fun execute(params: Params): Flow<Resource<List<Exam>>> =
        repository.getExamList(params)

    data class Params(
        val token: String = "",
        val id: Int = 0,
        val tahunAjaranSemesterId: Int = 0,
        val kelasId: Int = 0,
        val guruId: Int = 0,
        val matpelId: Int = 0,
        val jeniUjianId: Int = 0,
        val isActive: Int = 1,
        val isGroupBy: Int = 0
    )

}