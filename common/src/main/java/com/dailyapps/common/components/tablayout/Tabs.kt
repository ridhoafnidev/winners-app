package com.dailyapps.common.components.tablayout

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.dailyapps.common.Neutral500
import com.dailyapps.common.Primary
import com.dailyapps.common.White
import kotlinx.coroutines.launch
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.utils.NoRippleInteractionSource

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState, menus: List<String>) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        contentColor = Primary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 1.5.dp,
                color = Primary
            )
        }
    ) {
        menus.forEachIndexed { index, _ ->
            Tab(
                text = {
                  BaseText(
                      text = menus[index],
                      fontColor = if (pagerState.currentPage == index) Primary else Neutral500,
                  )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}