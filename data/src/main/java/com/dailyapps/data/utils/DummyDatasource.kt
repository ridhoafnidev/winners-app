package com.dailyapps.data.utils

import com.dailyapps.common.R
import com.dailyapps.entity.Menu

object DummyDatasource {
    fun generateMenus() = arrayListOf<Menu>(
        Menu("Absensi", R.drawable.ic_absen),
        Menu("Catatanku", R.drawable.ic_note),
        Menu("Nilai", R.drawable.ic_nilai),
        Menu("Ujian", R.drawable.ic_ujian),
    )

//    fun generateAbsents() = listOf<Absent>(
//        Absent(day = "Senin", date = "01-Januari-2023", "Hadir"),
//        Absent(day = "Selasa", date = "02-Januari-2023", "Tidak Hadir"),
//        Absent(day = "Rabu", date = "03-Januari-2023", "Hadir"),
//        Absent(day = "Kamis", date = "04-Januari-2023", "Tidak Hadir"),
//        Absent(day = "Jumat", date = "05-Januari-2023", "Hadir"),
//    )

//    fun generateNotes() = listOf<Note>(
//        Note(day = "Senin", date = "01-01-2023", "Terlambat"),
//        Note(day = "Selasa", date = "02-01-2023", "Berantam dengan teman sekelas, sampai babak belum"),
//        Note(day = "Rabu", date = "03-01-2023", "Ketahuan pacaran dan merokok dikelas"),
//        Note(day = "Kamis", date = "04-01-2023", "Bolos sekolah"),
//        Note(day = "Jumat", date = "05-01-2023", "Melawan dengan wali kelas"),
//        Note(day = "Sabtu", date = "06-01-2023", "Tidak mengerjakan PR dari guru"),
//    )

//    fun generateScore() = arrayListOf<Score>(
//        Score(1, "Mata Pelajaran", "Ilmu Pengetahuan Alam"),
//        Score(2, "Mata Pelajaran", "Matematika"),
//        Score(3, "Mata Pelajaran", "Matematika"),
//        Score(4, "Mata Pelajaran", "Matematika"),
//        Score(5, "Mata Pelajaran", "Matematika"),
//        Score(6, "Mata Pelajaran", "Matematika"),
//        Score(7, "Mata Pelajaran", "Matematika"),
//        Score(8, "Mata Pelajaran", "Matematika"),
//    )

    /*fun generateExams() = arrayListOf<Exam>(
        Exam(
            1,
            "Ujian tengah semester",
            "Semester 1",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            2,
            "Ujian tengah semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            3,
            "Ujian akhir semester",
            "Semester 1",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            4,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            5,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            6,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            7,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            8,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            9,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            10,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            11,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            12,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
        Exam(
            13,
            "Ujian akhir semester",
            "Semester 2",
            dateTime = "Rabu, 8 Februari 2023 - 13:30 WIB",
            time = "90 menit"
        ),
    )*/
}