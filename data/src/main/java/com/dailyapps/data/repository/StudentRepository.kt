package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.StudentRemoteDataSource
import com.dailyapps.domain.repository.IStudentRepository
import com.dailyapps.domain.usecase.StudentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Student
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val authRemoteDataSource: StudentRemoteDataSource
): IStudentRepository {
    override suspend fun getStudent(params: StudentUseCase.Params): Flow<Resource<List<Student>>> =
        authRemoteDataSource.getStudent(params)
}