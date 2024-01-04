package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.StudentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Student
import kotlinx.coroutines.flow.Flow

interface IStudentRepository {
    suspend fun getStudent(params: StudentUseCase.Params):Flow<Resource<List<Student>>>
}