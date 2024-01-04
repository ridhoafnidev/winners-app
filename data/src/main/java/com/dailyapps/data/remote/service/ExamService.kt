package com.dailyapps.data.remote.service

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ExamApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExamService {
    @GET("ujian")
    suspend fun getExampList(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
        @Query("tahun_ajaran_semester_id") tahunAjaranId: Int,
        @Query("kelas_id") kelasId: Int,
        @Query("guru_id") guruId: Int,
        @Query("matpel_id") matpelId: Int,
        @Query("jenis_ujian") jenisUjianId: Int,
        @Query("is_active") isActive: Int,
        @Query("is_group_by") isGroupBy: Int,
    ) : BaseResponse<List<ExamApiResponse>>
}