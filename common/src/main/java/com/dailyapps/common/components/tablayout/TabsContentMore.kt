package com.dailyapps.common.components.tablayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContentMore(
    pagerState: PagerState,
    tabType: TabTypeMore,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit,
    contentThree: @Composable () -> Unit,
    contentFour: @Composable () -> Unit,
) {
    HorizontalPager(state = pagerState, itemSpacing = 18.dp) { pager ->
        when(pager) {
            0 -> TabContentScreenMore(tabType, pager, contentOne, contentTwo, contentThree, contentFour)
            1 -> TabContentScreenMore(tabType, pager, contentOne, contentTwo, contentThree, contentFour)
            2 -> TabContentScreenMore(tabType, pager, contentOne, contentTwo, contentThree, contentFour)
            3 -> TabContentScreenMore(tabType, pager, contentOne, contentTwo, contentThree, contentFour)
        }
    }
}