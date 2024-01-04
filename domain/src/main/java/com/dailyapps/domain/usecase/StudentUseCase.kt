package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IStudentRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Student
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentUseCase @Inject constructor(
    private val repository: IStudentRepository
): ApiUseCaseParams<StudentUseCase.Params, List<Student>> {

    override suspend fun execute(params: Params): Flow<Resource<List<Student>>> = repository.getStudent(params)

    data class Params(val userId: Int, val token: String)

}

