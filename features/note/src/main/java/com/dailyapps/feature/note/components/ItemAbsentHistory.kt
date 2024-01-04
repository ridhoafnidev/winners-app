package com.dailyapps.feature.note.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Neutral300
import com.dailyapps.common.Primary
import com.dailyapps.common.White
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.common.utils.DateTime
import com.dailyapps.entity.Menu
import com.dailyapps.entity.AbsentDataItem

@SuppressLint("NewApi")
@Composable
fun <T> ItemAbsentHistory(
    data: T,
    modifier: Modifier = Modifier,
    onItemClick: (Menu) -> Unit = {},
) {
    val absent = data as AbsentDataItem
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Neutral100, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable { onItemClick },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                BaseText(
                    text = absent.tanggalAbsensi?.let {
                        DateTime.getIndoDayOfWeek(it)
                    } ?: "",
                    fontColor = Neutral300
                )
                BaseText(
                    text = absent.tanggalAbsensi?.let {
                        "${DateTime.convertToShort(date = it)}, ${absent.jamMasuk}"
                    } ?: "",
                    fontFamily = FontType.MEDIUM,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = modifier.padding(top = 8.dp)
                )
            }
            Box(
                modifier = modifier
                    .background(color = Primary, shape = RoundedCornerShape(8.dp)),

                ) {
                absent.jenisAbsensi?.uppercase()
                    ?.let {
                        BaseText(
                            text = it,
                            fontColor = White,
                            modifier = modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                        )
                    }
            }
        }
    }
}

/*
@Preview
@Composable
fun ItemAbsentHistoryPreview() {
    Surface() {
        ItemAbsentHistory(data = Absent(day = "Senin", date = "01-Januari-2023", "Hadir"))
    }
}*/
