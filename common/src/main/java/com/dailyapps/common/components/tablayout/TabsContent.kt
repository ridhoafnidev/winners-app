package com.dailyapps.common.components.tablayout

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    tabType: TabType,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit,
) {
    HorizontalPager(state = pagerState) {pager ->
        when(pager) {
            0 -> TabContentScreen(tabType, pager, contentOne, contentTwo)
            1 -> TabContentScreen(tabType, pager, contentOne, contentTwo)
        }
    }
}