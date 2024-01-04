package com.dailyapps.common.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun getCurrentWibLocalDateTime(): LocalDateTime {
    val wibZone = ZoneId.of("Asia/Jakarta")
    return LocalDateTime.now(wibZone)
}

fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val localDateTime = LocalDateTime.parse(this, formatter)
    val zoneId = ZoneId.of("Asia/Jakarta")
    val zonedDateTime = ZonedDateTime.of(localDateTime, zoneId)
    return zonedDateTime.toLocalDateTime()
}