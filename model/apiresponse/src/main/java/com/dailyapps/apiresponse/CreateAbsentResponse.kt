package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName


data class CreateAbsentResponse(
    @field:SerializedName("jenis_absensi_id")
    val jenisAbsensiId: String? = null,

    @field:SerializedName("tanggal_absensi")
    val tanggalAbsensi: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("jam_masuk")
    val jamMasuk: String? = null,

    @field:SerializedName("latitude")
    val latitude: String? = null,

    @field:SerializedName("tahun_ajaran_semester_id")
    val tahunAjaranSemesterId: String? = null,

    @field:SerializedName("siswa_id")
    val siswaId: String? = null,

    @field:SerializedName("longitude")
    val longitude: String? = null
)
