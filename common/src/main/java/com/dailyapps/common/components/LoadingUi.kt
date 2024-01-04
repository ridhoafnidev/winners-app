package com.dailyapps.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dailyapps.common.Primary

@Composable
fun LoadingUi(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LoadingIndicator(
            color = Primary,
            animationType = AnimationType.Bounce,
            animating = true
        )
    }
}

@Preview
@Composable
fun PreviewLoading() {
    LoadingUi()
}