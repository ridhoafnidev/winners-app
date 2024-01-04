package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ExamQuestionApiResponse(

	@field:SerializedName("ujian")
	val examItem: ExamQuestionItemApiResponse? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("ujian_id")
	val ujianId: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Int? = null,

	@field:SerializedName("jenis_soal")
	val jenisSoal: String? = null,

	@field:SerializedName("kunci_jawaban")
	val kunciJawaban: String? = null,

	@field:SerializedName("opsi_b")
	val opsiB: String? = null,

	@field:SerializedName("opsi_c")
	val opsiC: String? = null,

	@field:SerializedName("images_soal")
	val imagesSoal: String? = null,

	@field:SerializedName("opsi_a")
	val opsiA: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("opsi_d")
	val opsiD: String? = null,

	@field:SerializedName("opsi_e")
	val opsiE: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("bobot_nilai")
	val bobotNilai: String? = null,

	@field:SerializedName("text_soal")
	val textSoal: String? = null
)

data class ExamQuestionItemApiResponse(

	@field:SerializedName("nama_nilai_id")
	val namaNilaiId: String? = null,

	@field:SerializedName("is_start")
	val isStart: String? = null,

	@field:SerializedName("ujian_start")
	val ujianStart: String? = null,

	@field:SerializedName("is_active")
	val isActive: String? = null,

	@field:SerializedName("jenis_ujian")
	val jenisUjian: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("matpel_id")
	val matpelId: String? = null,

	@field:SerializedName("guru_id")
	val guruId: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Int? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("ujian_end")
	val ujianEnd: String? = null,

	@field:SerializedName("expired_duration")
	val expiredDuration: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("tahun_ajaran_semester_id")
	val tahunAjaranSemesterId: String? = null,

	@field:SerializedName("is_finish")
	val isFinish: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
