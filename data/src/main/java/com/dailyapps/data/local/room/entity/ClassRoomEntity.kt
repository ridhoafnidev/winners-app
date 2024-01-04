package com.dailyapps.data.local.room.entity

import androidx.room.Entity

@Entity(tableName = "class_room", primaryKeys = ["id"])
data class ClassRoomEntity(
    val updatedAt: Int? = null,
    val kelas: String? = null,
    val createdAt: Int? = null,
    val id: Int? = null,
    val deletedAt: String? = null
)


