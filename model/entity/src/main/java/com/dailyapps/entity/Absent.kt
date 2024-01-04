package com.dailyapps.entity

data class Absent(
    val data: List<AbsentDataItem>? = null,
)

data class AbsentDataItem(
    val jenisAbsensiId: Int? = null,
    val tanggalAbsensi: String? = null,
    val studentApiResponse: Student? = null,
    val latitude: String? = null,
    val createdAt: Int? = null,
    val jenisAbsensi: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val jamMasuk: String? = null,
    val tahunAjaranSemesterId: Int? = null,
    val id: Int? = null,
    val siswaId: Int? = null,
    val longitude: String? = null
)

data class Student(
    val nama: String? = null,
    val noHp: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val nisn: String? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val jenisKelamin: String? = null,
    val kelasId: Int? = null,
    val deletedAt: String? = null,
    val alamat: String? = null,
    val kelas: ClassRoom? = null,
    val namaKelas: String? = null
) {
    companion object {
        val EMPTY = Student()
    }
}

data class LinkItem(
    val active: Boolean? = null,
    val label: String? = null,
    val url: String? = null
)

