package com.dailyapps.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dailyapps.common.R

@Composable
fun ErrorUi(
    message: String,
    @DrawableRes imageRes: Int? = null,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
    title: String = stringResource(R.string.something_error_title),
    buttonText: String = stringResource(R.string.try_again)
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(modifier = Modifier.fillMaxWidth(),
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center, color =MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        BaseButton(text = buttonText) {
            onButtonClick.invoke()
        }
    }
}

@Preview
@Composable
fun previewErrorUi() {
    ErrorUi(
        message = stringResource(R.string.something_error_title),
        onButtonClick = { }
    )
}