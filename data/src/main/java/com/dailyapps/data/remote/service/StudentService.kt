package com.dailyapps.data.remote.service

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.StudentApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StudentService {
    @GET("siswa")
    suspend fun getStudentById(
        @Header("Authorization") token: String,
        @Query("user_id") userId: Int
    ) : BaseResponse<List<StudentApiResponse>>

}