package com.dailyapps.data.remote.service

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.NoteApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NoteService {
    @GET("pelanggaran")
    suspend fun getNoteById(
        @Header("Authorization") token: String,
        @Query("siswa_id") siswaId: Int,
        @Query("tahun_ajaran_semester_id") tahunAjaran: Int,
    ) : BaseResponse<List<NoteApiResponse>>

}