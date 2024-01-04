package com.dailyapps.entity

data class Exam(
    val guru: Guru? = null,
    val ujianStart: String? = null,
    val isActive: Int? = null,
    val jenisUjian: String? = null,
    val matpelId: Int? = null,
    val guruId: Int? = null,
    val token: String? = null,
    val ujianEnd: String? = null,
    val kelas: ClassRoom? = null,
    val tahunAjaranSemesterId: Int? = null,
    val matpel: Matpel? = null,
    val id: Int? = null,
    val kelasId: Int? = null,
    val expiredDuration: Int? = null,
    val tahunAjaranSemester: SchoolYear? = null,
    val isFinish: Int? = null,
    val isStart: Int? = null,
    val jenisNilai: JenisNilai? = null,
    val status: Int? = null
) {
    val ujian = if (jenisNilai?.jenisNilai?.equals("UTS", true) == true) "UJIAN TENGAH SEMESTER"
        else "UJIAN AKHIR SEMESTER"

    val start = isStart == 1

    val finish = isFinish == 1

    val studentCanAttemptExam = status == null

    val isStudentSuspended = status == 1

    val isStudentFinishTheExam = status == 0

    val isStudentAlreadyRedeemToken = status == 2

}