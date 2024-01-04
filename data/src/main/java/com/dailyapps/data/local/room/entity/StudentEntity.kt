package com.dailyapps.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "student"
)
data class StudentEntity(
    @PrimaryKey
    val id: Int? = null,
    val nama: String? = null,
    val noHp: String? = null,
    val foto: String? = null,
    val updatedAt: Int? = null,
    val nisn: String? = null,
    val createdAt: Int? = null,
    val jenisKelamin: String? = null,
    val kelasId: Int? = null,
    val kelas: String? = null,
    val deletedAt: String? = null,
    val alamat: String? = null
)