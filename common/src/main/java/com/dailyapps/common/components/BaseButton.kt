package com.dailyapps.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Primary
import com.dailyapps.common.Primary80
import com.dailyapps.common.fontMedium
import kotlinx.coroutines.CoroutineScope

enum class AnimationType {
    Bounce,
    LazyBounce,
    Fade,
}

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Primary,
    textColor: Color = White,
    sizeCorner: Dp = 16.dp,
    contentPaddingVer: Dp = 8.dp,
    contentPaddingHor: Dp = 16.dp,
    isLoading: Boolean = false,
    indicatorSpacing: Dp = 8.dp,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val contentAlpha by animateFloatAsState(targetValue = if (isLoading) 0f else 1f)
    val loadingAlpha by animateFloatAsState(targetValue = if (isLoading) 1f else 0f)

    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if (enabled) color else Primary80),
        shape = RoundedCornerShape(sizeCorner),
        contentPadding = PaddingValues(vertical = contentPaddingVer, horizontal = contentPaddingHor),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            LoadingIndicator(
                animating = isLoading,
                modifier = Modifier.graphicsLayer { alpha = loadingAlpha },
                color = White,
                indicatorSpacing = indicatorSpacing,
                animationType = AnimationType.Fade
            )
            Box(
                modifier = Modifier.graphicsLayer { alpha = contentAlpha }
            ) {
                Text(
                    text = text,
                    fontFamily = fontMedium,
                    fontSize = 16.sp,
                    color = textColor,
                )
            }
        }

    }
}

@Preview
@Composable
fun BaseButtonPreview() {
    BaseButton(text = "Button", onClick={})
}

@Stable
interface LoadingIndicatorState {
    operator fun get(index: Int): Float

    fun start(animationType: AnimationType, scope: CoroutineScope)
}

@Composable
fun rememberLoadingIndicatorState(
    animating: Boolean,
    animationType: AnimationType,
): LoadingIndicatorState {
    val state = remember {
        LoadingIndicatorStateImpl()
    }
    LaunchedEffect(key1 = animating) {
        if (animating) {
            state.start(animationType, this)
        }
    }
    return state
}
