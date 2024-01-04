package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class SubmitExamApiResponse(

	@field:SerializedName("jawaban")
	val jawaban: List<SubmitExamItemResponse>? = null
)

data class SubmitExamItemResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("nilai")
	val nilai: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("jawaban")
	val jawaban: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("soal_id")
	val soalId: Int? = null,

	@field:SerializedName("siswa_id")
	val siswaId: Int? = null
)
