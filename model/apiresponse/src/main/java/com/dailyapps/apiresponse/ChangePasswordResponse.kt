package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
	@field:SerializedName("message")
	val message: String? = null
)
