package com.dailyapps.feature.exam.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.entity.Exam
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Primary
import com.dailyapps.common.components.BaseRadioButton
import kotlinx.coroutines.launch
@Composable
fun <T> ItemTestExam(
    data: T,
    modifier: Modifier = Modifier,
    selected: Boolean,
    onValueChange: (Int) -> Unit,
    onItemClick: (Exam) -> Unit = {},
) {
    val exam = data as Exam
    val scaleA = remember { Animatable(initialValue = 1f) }
    val scaleB = remember { Animatable(initialValue = 1f) }
    val clickEnabled = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = selected) {
        if (selected) {
            clickEnabled.value = false
            val animateA = launch {
                scaleA.animateTo(
                    targetValue = 0.3f,
                    animationSpec = tween(
                        durationMillis = 50
                    )
                )
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            val animateB = launch {
                scaleB.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(
                        durationMillis = 50
                    )
                )
                scaleB.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
            }
            animateA.join()
            animateB.join()
            clickEnabled.value = true
        }
    }

    OutlinedCard(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp)),
        border = BorderStroke(2.dp, Neutral100)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            BaseText(
                text = "Nomor 1".uppercase(),
                modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top= 12.dp),
                fontColor = Primary,
                fontFamily = FontType.MEDIUM,
                fontSize = 10.sp
            )
            BaseText(
                text = "Tekanan dalam zat cair yang disebabkan oleh zat cair itu sendiri disebut...",
                modifier = modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                fontFamily = FontType.MEDIUM,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(2.dp).fillMaxWidth().background(Neutral100))
            BaseRadioButton(isColumn = true, items = listOf("Tekanan archimedes", "Tekanan hidrostatik", "Bunyi Hukum Pascal", "Bunyi Hukum Boyle")){

            }
        }
    }
}

