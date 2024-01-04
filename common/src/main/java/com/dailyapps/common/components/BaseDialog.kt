package com.dailyapps.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun BaseDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    confirmButtonText: String = "Confirm",
    cancelButtonText: String = "Cancel",
    onDismissRequest: () -> Unit = {},
    onConfirmClicked: () -> Unit = {},
    onCancelRequest: () -> Unit = {},
    cancelButtonVisible: Boolean = true
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 24.dp, horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            BaseText(
                text = title,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontColor = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseText(
                text = description,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontColor = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (cancelButtonVisible) {
                    TextButton(onClick = onCancelRequest) {
                        BaseText(
                            text = cancelButtonText,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontColor = MaterialTheme.colorScheme.primary,
                            fontFamily = FontType.MEDIUM
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                TextButton(onClick = onConfirmClicked) {
                    BaseText(
                        text = confirmButtonText,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontColor = MaterialTheme.colorScheme.primary,
                        fontFamily = FontType.MEDIUM
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewBaseDialog() {
    MaterialTheme {
        Surface {
            BaseDialog(
                title = "Apakah anda ingin menyelesaikan ujian?",
                description = "Ketika anda menyelesaikan ujian, anda tidak dapat melakukan ujian kembali. Pasti anda benar-benar telah menjawab soal-soal dengan benar!"
            )
        }
    }
}
