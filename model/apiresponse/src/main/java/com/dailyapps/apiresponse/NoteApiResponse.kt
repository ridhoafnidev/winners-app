package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class NoteApiResponse(

	@field:SerializedName("pelanggaran")
	val pelanggaran: String? = null,

	@field:SerializedName("tanggal_pelanggaran")
	val tanggalPelanggaran: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("tahun_ajaran_semester_id")
	val tahunAjaranSemesterId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null,

	@field:SerializedName("siswa_id")
	val siswaId: Int? = null,

)

