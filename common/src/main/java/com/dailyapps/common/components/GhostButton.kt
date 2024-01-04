package com.dailyapps.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.dailyapps.common.Placeholder

@Composable
fun GhostButton(
    modifier: Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    onCLick:() -> Unit
) {
    TextButton(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Placeholder
        ),
        onClick = onCLick
    ) {
        Text(text = text, style = style)
    }
}