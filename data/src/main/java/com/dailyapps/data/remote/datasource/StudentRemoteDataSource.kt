package com.dailyapps.data.remote.datasource

import com.dailyapps.data.local.datasource.MasterLocalDataSource
import com.dailyapps.data.remote.SafeApiCall
import com.dailyapps.data.remote.service.StudentService
import com.dailyapps.data.utils.DataMapper.toDomainStudents
import com.dailyapps.data.utils.DataMapper.toEntityStudents
import com.dailyapps.domain.usecase.StudentUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRemoteDataSource @Inject constructor(
    private val service: StudentService,
    private val masterLocalDataSource: MasterLocalDataSource
) : SafeApiCall {
    suspend fun getStudent(params: StudentUseCase.Params): Flow<Resource<List<Student>>> = flow {
        runCatching {
            val apiResult = safeApiCall { service.getStudentById(params.token, params.userId) }
            val apiResource: Resource<List<Student>> = when (apiResult) {
                is Resource.Error -> Resource.Error(apiResult.msg)
                is Resource.Success -> with(apiResult.data) {
                    toDomainStudents().run {
                        masterLocalDataSource.replaceAllStudents(toEntityStudents())
                        Resource.Success(this)
                    }
                }
                is Resource.Loading -> Resource.Loading
            }
            emit(apiResource)
        }
        .onFailure {
            Resource.Error("Unknow error")
        }

        /*return mapFromApiResponse(
            result = apiCall(true) {
                service.getStudentById(params.token, params.userId)
            }, mapper
        )*/

    }
}