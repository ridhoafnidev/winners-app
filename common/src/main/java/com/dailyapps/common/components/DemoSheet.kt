package com.dailyapps.common.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    when {
        sheetState.isVisible -> {
            ModalBottomSheet(modifier = Modifier.fillMaxSize(),
            onDismissRequest = onDismiss,
            sheetState = sheetState
            ) {
                content()
            }
        }
    }
}