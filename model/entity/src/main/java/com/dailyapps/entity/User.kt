package com.dailyapps.entity

data class User(
    val profilePhotoUrl: String? = null,
    val roles: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val email: String? = null,
    val username: String? = null,
    val token: String? = null,
    val tokenType: String? = null,
) {
    companion object {
        val EMPTY = User(profilePhotoUrl = "", roles = "", name = "", id = 0, email = "", username = "", token = "", tokenType = "")
    }
}

val User.isStudent
    get() = roles == "SISWA"
val User.isAdmin
    get() = roles == "ADMIN"
