package com.dailyapps.feature.exam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.entity.Exam
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.scale
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Neutral300
import com.dailyapps.common.Primary
import kotlinx.coroutines.launch
@Composable
fun <T> ItemExam(
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

    Column(
        modifier = modifier
            .scale(scale = scaleB.value)
            .border(width = 1.dp, color = Neutral100, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = if (selected) Primary else Color.White)
            .clickable(enabled = clickEnabled.value) {
                onItemClick(exam)
                onValueChange(data.id ?: 0)
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            BaseText(
                text = exam.ujian,
                modifier = modifier.fillMaxWidth(),
                fontColor = if (selected) Color.White else Neutral300,
                fontFamily = FontType.MEDIUM,
                fontSize = 10.sp
            )
            BaseText(
                text = "Semester ${exam.tahunAjaranSemester?.semester ?: ""}",
                modifier = modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                fontColor = if (selected) Color.White else Neutral300,
                fontFamily = FontType.MEDIUM,
                fontSize = 16.sp
            )
        }
    }
}

