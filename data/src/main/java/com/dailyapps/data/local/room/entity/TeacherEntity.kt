package com.dailyapps.data.local.room.entity

import androidx.room.Entity

@Entity(tableName = "teacher", primaryKeys = ["id"])
data class TeacherEntity(
    val nama: String? = null,
    val noHp: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val noGuru: String? = null,
    val jenisKelamin: String? = null,
    val deletedAt: String? = null,
    val alamat: String? = null
)


