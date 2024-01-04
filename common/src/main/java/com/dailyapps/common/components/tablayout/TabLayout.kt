package com.dailyapps.common.components.tablayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    menus: List<String>,
    tabType: TabType,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = 2)
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp),
    ) {
        Tabs(pagerState = pagerState, menus = menus)
        TabsContent(pagerState = pagerState, tabType, contentOne, contentTwo)
    }
}

enum class TabType {
    HISTORY, SCORE
}