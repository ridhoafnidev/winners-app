package com.dailyapps.common.components.tablayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TabContentScreenMore(
    tabType: TabTypeMore,
    pager: Int,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit,
    contentThree: @Composable () -> Unit,
    contentFour: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        when {
            (tabType == TabTypeMore.TASK ||
                tabType == TabTypeMore.DAILY_TEST ||
                tabType == TabTypeMore.UAS ||
                tabType == TabTypeMore.UTS) && pager == 0 -> {
                contentOne()
            }
            (tabType == TabTypeMore.TASK ||
                tabType == TabTypeMore.DAILY_TEST ||
                tabType == TabTypeMore.UAS ||
                tabType == TabTypeMore.UTS) && pager == 1 -> {
                contentTwo()
            }
            (tabType == TabTypeMore.TASK ||
                tabType == TabTypeMore.DAILY_TEST ||
                tabType == TabTypeMore.UAS ||
                tabType == TabTypeMore.UTS) && pager == 2 -> {
                contentThree()
            }
            (tabType == TabTypeMore.TASK ||
                tabType == TabTypeMore.DAILY_TEST ||
                tabType == TabTypeMore.UAS ||
                tabType == TabTypeMore.UTS) && pager == 3 -> {
                contentFour()
            }
        }
    }
}