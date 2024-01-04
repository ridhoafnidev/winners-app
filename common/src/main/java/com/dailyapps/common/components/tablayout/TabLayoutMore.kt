package com.dailyapps.common.components.tablayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayoutMore(
    modifier: Modifier = Modifier,
    menus: List<String>,
    tabType: TabTypeMore,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit,
    contentThree: @Composable () -> Unit,
    contentFour: @Composable () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = 4)
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(top = 12.dp),
    ) {
        Tabs(pagerState = pagerState, menus = menus)
        TabsContentMore(pagerState = pagerState, tabType, contentOne, contentTwo, contentThree, contentFour)
    }
}

enum class TabTypeMore {
    DAILY_TEST, TASK, UAS, UTS
}