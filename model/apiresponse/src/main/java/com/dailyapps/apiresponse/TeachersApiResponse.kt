package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class TeachersApiResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("no_guru")
	val noGuru: String? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
