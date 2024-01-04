package com.dailyapps.entity

data class SubmitExamRequest(
    val soalId: Int = 0,
    val siswaId: Int = 0,
    val jawaban: String = ""
)
