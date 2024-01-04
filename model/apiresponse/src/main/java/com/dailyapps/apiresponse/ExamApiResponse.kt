package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ExamApiResponse(

    @field:SerializedName("guru")
    val guru: GuruApiResponse? = null,

    @field:SerializedName("ujian_start")
    val ujianStart: String? = null,

    @field:SerializedName("is_active")
    val isActive: Int? = null,

    @field:SerializedName("jenis_ujian")
    val jenisUjian: String? = null,

    @field:SerializedName("matpel_id")
    val matpelId: Int? = null,

    @field:SerializedName("guru_id")
    val guruId: Int? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("ujian_end")
    val ujianEnd: String? = null,

    @field:SerializedName("tahun_ajaran_semester_id")
    val tahunAjaranSemesterId: Int? = null,

    @field:SerializedName("matpel")
    val matpel: MatpelApiResponse? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("kelas_id")
    val kelasId: Int? = null,

    @field:SerializedName("expired_duration")
    val expiredDuration: Int? = null,

    @field:SerializedName("tahun_ajaran_semester")
    val tahunAjaranSemester: SchoolYearApiResponse? = null,

    @field:SerializedName("is_finish")
    val isFinish: Int? = null,

    @field:SerializedName("is_start")
    val isStart: Int? = null,

    @field:SerializedName("jenis_nilai")
    val jenisNilai: JenisNilaiApiResponse? = null,

    @field:SerializedName("status")
    val status: Int? = null,

)
