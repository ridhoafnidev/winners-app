package com.dailyapps.entity

data class Score(
    val jenisNilai: JenisNilai? = null,
    val namaNilaiId: Int? = null,
    val siswa: Siswa? = null,
    val guru: Guru? = null,
    val nilai: Int? = null,
    val createdAt: Int? = null,
    val matpelId: Int? = null,
    val jenisNilaiId: Int? = null,
    val guruId: Int? = null,
    val deletedAt: Int? = null,
    val updatedAt: Int? = null,
    val namaNilai: NamaNilai? = null,
    val kelas: Kelas? = null,
    val tahunAjaranSemesterId: Int? = null,
    val matpel: Matpel? = null,
    val id: Int? = null,
    val kelasId: Int? = null,
    val siswaId: Int? = null,
    val tahunAjaranSemester: TahunAjaranSemester? = null
)

data class Siswa(
    val nama: String? = null,
    val noHp: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val nisn: String? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val jenisKelamin: String? = null,
    val kelasId: Int? = null,
    val deletedAt: Any? = null,
    val alamat: String? = null
)

data class NamaNilai(
    val namaNilai: String? = null,
    val id: Int? = null
)

data class TahunAjaranSemester(
    val updatedAt: Int? = null,
    val createdAt: Int? = null,
    val semester: String? = null,
    val id: Int? = null,
    val tahunAjaran: String? = null,
    val deletedAt: Any? = null
)

data class Guru(
    val nama: String? = null,
    val noHp: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val noGuru: String? = null,
    val jenisKelamin: String? = null,
    val deletedAt: Int? = null,
    val alamat: String? = null
)

data class Kelas(
    val updatedAt: Int? = null,
    val kelas: String? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val deletedAt: Int? = null
)

data class JenisNilai(
    val jenisNilai: String? = null,
    val id: Int? = null
)

data class Matpel(
    val updatedAt: Int? = null,
    val createdAt: Int? = null,
    val matpel: String? = null,
    val id: Int? = null,
    val deletedAt: Int? = null
)