package com.dailyapps.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Primary
import com.dailyapps.common.Primary50
import com.dailyapps.common.R
import com.dailyapps.common.White
import com.dailyapps.common.components.BaseButton
import com.dailyapps.common.fontMedium
import com.dailyapps.common.fontRegular

@Composable
fun VisiMisi(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Image(
            modifier = modifier
                .height(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            Brush.horizontalGradient(
                                0.5f to Primary.copy(alpha = 0.9F),
                                10F to Color.Transparent,
                            )
                        )
                    }
                },
            painter = painterResource(R.drawable.gedung),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop,
        )
        Column(modifier = modifier.padding(20.dp)) {
            Text(text = "Visi & Misi", fontSize = 32.sp, color = White, fontFamily = fontMedium, fontWeight = FontWeight.Bold)
            Text(text = "“Terwujudnya sekolah yang unggul dalam disiplin, tangguh dan berempati dalam terang kristiani”", fontSize = 14.sp, color = White, fontFamily = fontRegular, modifier = Modifier.padding(top = 12.dp))
            BaseButton(text = "Selengkapnya", color = Primary50, textColor = Primary, sizeCorner = 8.dp, modifier = Modifier.padding(top = 20.dp), contentPaddingVer=2.dp) {
                onClick()
            }
        }
    }
}

@Preview
@Composable
fun VisiMisiPreview() {
    VisiMisi {}
}