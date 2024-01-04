package com.dailyapps.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Neutral300
import com.dailyapps.common.Neutral900
import com.dailyapps.common.Neutral_900
import com.dailyapps.common.R

@Composable
fun BaseAppBar(
    title: String,
    onClickBack:()->Unit,
    modifier: Modifier = Modifier,
    @DrawableRes menuIconResource: Int? = null,
    elevation: Dp = 2.dp,
    subTitle: String? = null,
    onMenuClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Column {
                BaseText(
                    text = subTitle?.let { title.uppercase() } ?: title,
                    fontFamily = subTitle?.let { FontType.MEDIUM } ?: FontType.SEMI_BOLD,
                    fontSize = subTitle?.let { 16.sp } ?: 24.sp,
                    fontColor = subTitle?.let { Neutral300 } ?: Neutral900
                )
                subTitle?.let { subtitle ->
                    BaseText(
                        text = subtitle,
                        fontFamily = FontType.MEDIUM,
                        fontSize = 16.sp,
                        fontColor = MaterialTheme.colors.Neutral_900
                    )
                }
            }
        },
        elevation = elevation,
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.img_desc_icon_back),
                    tint = Color.Black
                )
            }
        },
        actions = {
            if (menuIconResource != null) IconButton(onClick = { onMenuClick() }) {
                Icon(
                    painter = painterResource(id = menuIconResource),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        },
        backgroundColor = Color.White,
        modifier = modifier
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, name = "dark")
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, name = "light")
@Composable
fun AppBarPreview() {
    BaseAppBar("Title", onClickBack = {}, onMenuClick = {})
}
