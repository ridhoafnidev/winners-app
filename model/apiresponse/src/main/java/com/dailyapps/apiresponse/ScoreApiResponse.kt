package com.dailyapps.apiresponse

import com.google.gson.annotations.SerializedName

data class ScoreApiResponse(

	@field:SerializedName("jenis_nilai")
	val jenisNilai: JenisNilaiApiResponse? = null,

	@field:SerializedName("nama_nilai_id")
	val namaNilaiId: Int? = null,

	@field:SerializedName("siswa")
	val siswa: SiswaApiResponse? = null,

	@field:SerializedName("guru")
	val guru: GuruApiResponse? = null,

	@field:SerializedName("nilai")
	val nilai: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("matpel_id")
	val matpelId: Int? = null,

	@field:SerializedName("jenis_nilai_id")
	val jenisNilaiId: Int? = null,

	@field:SerializedName("guru_id")
	val guruId: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("nama_nilai")
	val namaNilai: NamaNilaiApiResponse? = null,

	@field:SerializedName("kelas")
	val kelas: KelasApiResponse? = null,

	@field:SerializedName("tahun_ajaran_semester_id")
	val tahunAjaranSemesterId: Int? = null,

	@field:SerializedName("matpel")
	val matpel: MatpelApiResponse? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("kelas_id")
	val kelasId: Int? = null,

	@field:SerializedName("siswa_id")
	val siswaId: Int? = null,

	@field:SerializedName("tahun_ajaran_semester")
	val tahunAjaranSemester: TahunAjaranSemesterApiResponse? = null
)

data class SiswaApiResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("foto")
	val foto: Any? = null,

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
	val deletedAt: Any? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)

data class NamaNilaiApiResponse(

	@field:SerializedName("nama_nilai")
	val namaNilai: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class TahunAjaranSemesterApiResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("semester")
	val semester: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tahun_ajaran")
	val tahunAjaran: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)

data class GuruApiResponse(

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
	val deletedAt: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)

data class KelasApiResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)

data class JenisNilaiApiResponse(

	@field:SerializedName("jenis_nilai")
	val jenisNilai: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class MatpelApiResponse(

	@field:SerializedName("updated_at")
	val updatedAt: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Int? = null,

	@field:SerializedName("matpel")
	val matpel: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Int? = null
)
