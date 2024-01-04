package com.dailyapps.common.components.BottomSheet

sealed class Content {
    data class Center(val valueText: String): Content()
    data class Left(val valueText: String): Content()
}
