package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ExamRulesResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Int? = null
)
