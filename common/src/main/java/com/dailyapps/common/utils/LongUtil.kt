package com.dailyapps.common.utils

fun Long.toTimeFormat(): String {
    return String.format("%02d", this)
}