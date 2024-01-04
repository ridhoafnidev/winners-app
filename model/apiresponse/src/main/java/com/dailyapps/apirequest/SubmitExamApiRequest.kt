package com.dailyapps.apirequest

import com.google.gson.annotations.SerializedName

class SubmitExamApiRequest(
    @field:SerializedName("jawaban")
    val jawaban: List<SubmitExamRequestItem>
)

data class SubmitExamRequestItem(
    @field:SerializedName("soal_id")
    val soalId: Int = 0,
    @field:SerializedName("siswa_id")
    val siswaId: Int = 0,
    @field:SerializedName("jawaban")
    val jawaban: String = ""
)
