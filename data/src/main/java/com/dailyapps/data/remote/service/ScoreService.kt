package com.dailyapps.data.remote.service

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ScoreApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ScoreService {
    @GET("nilai")
    suspend fun getScoreStudent(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
        @Query("siswa_id") siswaId: Int,
        @Query("tahun_ajaran_semester_id") tahunAjaranId: Int,
        @Query("nama_nilai_id") namaNilaiId: Int,
        @Query("jenis_nilai_id") jenisNilaiId: Int,
        @Query("kelas_id") kelasId: Int,
        @Query("matpel_id") matpelId: Int,
        @Query("is_order") isOrder: Int,
    ): BaseResponse<List<ScoreApiResponse>>
}