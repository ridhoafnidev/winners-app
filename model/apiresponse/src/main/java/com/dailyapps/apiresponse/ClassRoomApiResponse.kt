package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ClassRoomApiResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null
)
