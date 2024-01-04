package com.dailyapps.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(
    @ColumnInfo(name = "profile_photo_url")
    val profilePhotoUrl: String? = null,

    @ColumnInfo(name = "roles")
    val roles: String? = null,

    @ColumnInfo(name = "username")
    @PrimaryKey
    val username: String,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,
)