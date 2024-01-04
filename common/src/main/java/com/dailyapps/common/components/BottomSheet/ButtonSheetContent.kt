package com.dailyapps.common.components.BottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    state: BottomSheetState,
    coroutineScope: CoroutineScope,
    sheetState: ModalBottomSheetState
) {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .heightIn(
            (configuration.screenHeightDp * 0.25).dp,
            (configuration.screenHeightDp * 0.75).dp
        )
        .wrapContentWidth(unbounded = false)
        .wrapContentHeight(unbounded = true)
        .padding(24.dp, 24.dp, 24.dp, 32.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = state.alignValue) {
            state.imageResourceId?.let {
                header(state.imageResourceId!!)
            }
            Spacer(modifier = Modifier.height(24.dp))
            content(state.titleText, state.valueText)
            Spacer(modifier = Modifier.height(24.dp))
            footer(
                negativeLabel = state.negativeLabel,
                negativeButton = state.negativeButton,
                positiveLabel = state.positiveLabel,
                positiveButton = state.positiveButton,
                onClickNegative = state.onClickNegative,
                onClickPositive = state.onClickPositive,
                coroutine = coroutineScope,
                sheetState =sheetState
            )
        }
    }
}

@Composable
fun header(
    imageResourceId: Int
) {
    Box(modifier = Modifier) {
        Image(painter = painterResource(id = imageResourceId), contentDescription = "Image")
    }
}

@Composable
fun content(
    titleText: String,
    valueText: String
) {
    Text(text = titleText)
    Spacer(modifier = Modifier.height(24.dp))
    Text(text = valueText)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun footer(
    negativeLabel: String,
    negativeButton: Boolean,
    positiveLabel: String,
    positiveButton: Boolean,
    onClickNegative: (() -> Unit)?,
    onClickPositive: (() -> Unit)?,
    coroutine: CoroutineScope,
    sheetState: ModalBottomSheetState
) {
    if (positiveButton) {
        Button(onClick = onClickPositive!!) {
            Text(text = positiveLabel)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    if (negativeButton) {
        Button(onClick = onClickNegative!!) {
            Text(text = negativeLabel)
        }
    }
}