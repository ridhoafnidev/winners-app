package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class AbsentApiResponse(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("data")
	val data: List<AbsentDataItemApiResponse>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: String? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: String? = null,

	@field:SerializedName("first_page_url")
	val firstPageUrl: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("last_page_url")
	val lastPageUrl: String? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("links")
	val links: List<LinkItemApiResponse>? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class AbsentDataItemApiResponse(

	@field:SerializedName("jenis_absensi_id")
	val jenisAbsensiId: Int? = null,

	@field:SerializedName("tanggal_absensi")
	val tanggalAbsensi: String? = null,

	@field:SerializedName("siswa")
	val studentApiResponse: StudentApiResponse? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("jenis_absensi")
	val jenisAbsensi: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("jam_masuk")
	val jamMasuk: String? = null,

	@field:SerializedName("tahun_ajaran_semester_id")
	val tahunAjaranSemesterId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("siswa_id")
	val siswaId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)

data class StudentApiResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("nisn")
	val nisn: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("kelas_id")
	val kelasId: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("kelas")
	val kelas: ClassRoomApiResponse? = null
)

data class LinkItemApiResponse(

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
