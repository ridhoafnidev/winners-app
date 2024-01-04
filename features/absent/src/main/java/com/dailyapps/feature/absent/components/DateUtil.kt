package com.dailyapps.feature.absent.components

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import java.util.Locale

@SuppressLint("NewApi")
object DateUtil {
    fun getCurrentDate(
        date: Date = Date(),
        pattern: String = "yyyy-MM-dd"
    ): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(date)
    }

    inline val THIS_TIME: LocalTime
        get() = LocalTime.now()

    inline val THIS_DATE: LocalDate
        get() = LocalDate.now()

    fun timeDontAbsent(): Boolean {
        val time = Date()
        return time toString "hh:mm" == "16:00"
    }

    infix fun Date.toString(format: String): String {
        val timeFormat = SimpleDateFormat(format, Locale.getDefault())
        return timeFormat.format(this)
    }

}