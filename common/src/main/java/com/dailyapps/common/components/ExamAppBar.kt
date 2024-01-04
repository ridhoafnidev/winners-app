package com.dailyapps.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Neutral300
import com.dailyapps.common.Neutral900
import com.dailyapps.common.R

@ExperimentalMaterial3Api
@Composable
fun ExamAppBar(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Neutral300,
    navigationBackClicked:() -> Unit,
    subtitle: String,
    subtitleColor: Color = Neutral900,
) {
    Column {
        TopAppBar(
            title = {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    BaseText(
                        text = title,
                        fontFamily = FontType.MEDIUM,
                        fontSize = 12.sp,
                        fontColor = titleColor,
                        lineHeight = 20.sp,
                        letterSpacing = 1.2.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BaseText(
                        text = subtitle,
                        fontFamily = FontType.MEDIUM,
                        fontSize = 16.sp,
                        fontColor = subtitleColor,
                        letterSpacing = 0.16.sp
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navigationBackClicked() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.img_desc_icon_back),
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colors.background
            ),
            modifier = modifier
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = Neutral100
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, name = "dark")
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, name = "light")
@Composable
fun ExamAppBarPreview() {
    BaseAppBar("Title", onClickBack = {}, onMenuClick = {})
}
