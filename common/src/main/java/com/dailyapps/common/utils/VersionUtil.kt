package com.dailyapps.common.utils

import com.dailyapps.common.BuildConfig


val appVersion
    get() = BuildConfig.VERSION_NAME

val formattedAppVersion
    get() ="v $appVersion"

fun isUpdateRequired(latestVersion: String): Boolean {
    val current = extractSematicVersioning(appVersion) ?: return false
    val latest = extractSematicVersioning(latestVersion) ?: return false
    return latest.first > current.first || latest.second > current.second || latest.third > current.third
}

private fun extractSematicVersioning(version: String) =
    version.split(".").takeIf {
        it.size == 3
    }?.map {
        it.toInt()
    }?.let {
        Triple(it[0], it[1], it[2])
    }