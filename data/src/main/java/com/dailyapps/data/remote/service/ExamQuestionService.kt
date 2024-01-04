package com.dailyapps.data.remote.service

import com.dailyapps.apirequest.SubmitExamApiRequest
import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ExamQuestionApiResponse
import com.dailyapps.apiresponse.ExamRulesResponse
import com.dailyapps.apiresponse.SubmitExamApiResponse
import com.dailyapps.apiresponse.ChangeStudentExamsStateApiResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ExamQuestionService {

    @GET("soal")
    suspend fun getExamQuestion(
        @Header("Authorization") token: String,
        @Query("ujian_id") ujianId: Int
    ): BaseResponse<List<ExamQuestionApiResponse>>

    @POST("jawaban/create")
    suspend fun submitExam(
        @Header("Authorization") token: String,
        @Body submitExamApiRequest: SubmitExamApiRequest
    ): BaseResponse<SubmitExamApiResponse>

    @GET("peraturan")
    suspend fun getExamRules(
        @Header("Authorization") token: String
    ): BaseResponse<List<ExamRulesResponse>>

    @FormUrlEncoded
    @POST("ujian/status")
    suspend fun changeStudentExamsStatus(
        @Header("Authorization") token: String,
        @Field("siswa_id") studentId: Int,
        @Field("ujian_id") examId: Int,
        @Field("status") status: Int,
    ): BaseResponse<ChangeStudentExamsStateApiResponse>

}