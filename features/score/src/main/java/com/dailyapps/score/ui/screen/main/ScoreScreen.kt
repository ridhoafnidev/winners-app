package com.dailyapps.score.ui.screen.main

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dailyapps.common.EmptyList
import com.dailyapps.common.components.ErrorUi
import com.dailyapps.common.components.LoadingUi
import com.dailyapps.common.utils.NavRoute
import com.dailyapps.entity.Score
import com.dailyapps.score.R
import com.dailyapps.score.components.ItemScore
import com.dailyapps.score.ui.screen.ScoreScreenState
import com.dailyapps.score.ui.screen.ScoreViewModel
import com.dailyapps.score.ui.screen.detail.ScoreType

@Composable
fun ScoreScreen(
    navHostController: NavHostController,
    viewModel: ScoreViewModel
) {

    val scoreResult by viewModel.scoreScreenState.collectAsState()
    val currentTokenTypeId by viewModel.currentTokenTypeStudentId.collectAsState()
    val masterSchoolYear by viewModel.masterSchoolYear.collectAsState()
    val currentStudent by viewModel.currentClass.collectAsState()

    val studentId = currentStudent?.id ?: 0

    LaunchedEffect(currentTokenTypeId) {
        currentTokenTypeId.apply {
            if (first.isNotEmpty() && second.isNotEmpty() && masterSchoolYear.isNotEmpty()) {
                val tahunAjaran = masterSchoolYear.first().id ?: 0
                viewModel.getScoreStudent(
                    token = "${currentTokenTypeId.second} ${currentTokenTypeId.first}",
                    siswaId = studentId,
                    tahunAjaranId = tahunAjaran,
                    isOrder = 1,
                    type = ScoreType.OTHER
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.label_nilai))
                },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (scoreResult) {
                is ScoreScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LoadingUi(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is ScoreScreenState.Success -> {
                    (scoreResult as ScoreScreenState.Success).score.let { scores ->
                        if (scores.isNotEmpty()) {
                            ScoreList(scores, navHostController)
                        } else EmptyList()
                    }

                }

                is ScoreScreenState.Error -> {
                    (scoreResult as ScoreScreenState.Error).message.let { msg ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            ErrorUi(message = msg, onButtonClick = {
                                viewModel.getScoreStudent(
                                    token = "${currentTokenTypeId.second} ${currentTokenTypeId.first}",
                                    siswaId = studentId,
                                    tahunAjaranId = masterSchoolYear.first().id ?: 0,
                                    isOrder = 1,
                                    type = ScoreType.OTHER
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreList(data: List<Score>, navHostController: NavHostController) {
    var selected by remember {
        mutableStateOf(0)
    }

    if (data.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { score ->
                ItemScore(
                    data = score,
                    onValueChange = { selected = score.id ?: 0 },
                    selected = selected == score.id,
                    onItemClick = {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navHostController.navigate("${NavRoute.scoreDetailScreen}/${score.matpelId}")
                        }, 200)
                    })
            }
        }
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
//@Composable
//fun ScoreScreenPreview() {
//    val navHostController = rememberNavController()
//    ScoreScreen(navHostController)
//}