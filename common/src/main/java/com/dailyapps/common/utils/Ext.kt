package com.dailyapps.common.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

infix fun LocalDate.withFormat(pattern: String): String =
    withFormat(pattern, Locale.getDefault())

fun LocalDate.withFormat(pattern: String, locale: Locale = Locale.getDefault()): String =
    format(DateTimeFormatter.ofPattern(pattern, locale))

fun String.toLocalDate(): LocalDate = LocalDate.parse(this)