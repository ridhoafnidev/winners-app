package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("data")
	val data: T,
)

data class Meta(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
