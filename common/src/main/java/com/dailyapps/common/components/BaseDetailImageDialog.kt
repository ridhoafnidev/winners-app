package com.dailyapps.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun BaseDetailImageDialog(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onDismissRequest: () -> Unit
) {
    val zoomableImageState = rememberZoomState()
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = "Detail soal gambar",
                modifier = Modifier
                    .fillMaxSize()
                    .zoomable(zoomableImageState)
                    .align(Alignment.Center)
            )

            IconButton(
                onClick = onDismissRequest,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.4f))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Tutup detail ujian gambar"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBaseDetailImageDialog() {
    MaterialTheme {
        Surface {
            BaseDetailImageDialog(
                imageUrl = "https://smpsantoyosefduri.sch.id/images/soal/1691641707.jpg",
                onDismissRequest = {}
            )
        }
    }
}