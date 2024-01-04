package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class SchoolYearApiResponse(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("semester")
	val semester: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tahun_ajaran")
	val tahunAjaran: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null
)
