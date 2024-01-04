package com.dailyapps.data.remote.service

import com.dailyapps.apiresponse.AbsentDataItemApiResponse
import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.CreateAbsentResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AbsentService {

    @GET("absensi")
    suspend fun getAbsentById(
        @Header("Authorization") token: String,
        @Query("siswa_id") siswaId: Int,
        @Query("tahun_ajaran_semester_id") tahunAjaran: Int
    ) : BaseResponse<List<AbsentDataItemApiResponse>>

    @FormUrlEncoded
    @POST("absensi/create")
    suspend fun createAbsent(
        @Header("Authorization") token: String,
        @Field("siswa_id") siswaId: Int,
        @Field("jam_masuk") jamMasuk: String,
        @Field("tahun_ajaran_semester_id") tahunAjaranSemesterId: Int,
        @Field("tanggal_absensi") tanggalAbsensi: String,
        @Field("jenis_absensi_id") jenisAbsensiId: Int,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
        @Field("foto") foto: String,
    ): BaseResponse<CreateAbsentResponse>

}