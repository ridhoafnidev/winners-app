package com.dailyapps.entity

data class SchoolYear(
    val updatedAt: String? = null,
    val createdAt: String? = null,
    val semester: String? = null,
    val id: Int? = null,
    val tahunAjaran: String? = null,
    val deletedAt: String? = null
) {
    companion object {
        val EMPTY = SchoolYear("", "", "", 0, "", "")
    }
}

