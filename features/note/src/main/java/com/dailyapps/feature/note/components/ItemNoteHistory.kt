package com.dailyapps.feature.note.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Neutral300
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.common.utils.DateTime
import com.dailyapps.entity.Menu
import com.dailyapps.entity.Note
import com.dailyapps.feature.note.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T>ItemNoteHistory(
    data: T,
    modifier: Modifier = Modifier,
    onItemClick: (Menu) -> Unit = {},
) {
    val note = data as Note
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Neutral100, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable { onItemClick },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            BaseText(
                text = stringResource(R.string.day_date),
                fontColor = Neutral300,
                fontSize = 12.sp,
                modifier = modifier.fillMaxWidth()
            )
            BaseText(
                text = "${DateTime.getIndoDayOfWeek(note.tanggalPelanggaran ?: "")}, ${note.tanggalPelanggaran}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth(),
            )
            Divider(modifier = modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = com.dailyapps.common.Divider)
            )
            BaseText(
                text = stringResource(R.string.note_type),
                fontColor = Neutral300,
                fontSize = 12.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            BaseText(
                text = note.pelanggaran ?: "",
                fontFamily = FontType.SEMI_BOLD,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            )
        }

    }
}

@Preview
@Composable
fun ItemNoteHistoryPreview() {
    //ItemNoteHistory()
}