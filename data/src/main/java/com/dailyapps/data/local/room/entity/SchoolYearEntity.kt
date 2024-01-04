package com.dailyapps.data.local.room.entity

import androidx.room.Entity

@Entity(tableName = "school_year", primaryKeys = ["id"])
data class SchoolYearEntity(
    val updatedAt: String? = null,
    val createdAt: String? = null,
    val semester: String? = null,
    val id: Int? = null,
    val tahunAjaran: String? = null,
    val deletedAt: String? = null
)



