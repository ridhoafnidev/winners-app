package com.dailyapps.feature.exam.pages.list

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dailyapps.common.EmptyList
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.components.ErrorUi
import com.dailyapps.common.components.LoadingUi
import com.dailyapps.common.utils.NavRoute
import com.dailyapps.entity.Exam
import com.dailyapps.entity.SchoolYear
import com.dailyapps.feature.exam.ExamScreenState
import com.dailyapps.feature.exam.ExamViewModel
import com.dailyapps.feature.exam.components.ItemExam
import com.dailyapps.feature.exam.pages.detail.ExamScreenType

fun getToken(currentToken: Pair<String, String>) =
    "${currentToken.second} ${currentToken.first}"

fun getCurrentSchoolYear(year: SchoolYear) = year.id ?: 0

@Composable
fun ListExamPage(
    navController: NavHostController,
    viewModel: ExamViewModel
) {

    val examScreenState by viewModel.examScreenState.collectAsState()
    val currentTokenTypeStudentId by viewModel.currentTokenTypeStudentId.collectAsState()
    val masterSchoolYear by viewModel.masterSchoolYear.collectAsState()
    val currentClass by viewModel.currentClass.collectAsState()

    val currentSchoolYear by lazy {
        masterSchoolYear.first()
    }

    val studentId = currentClass?.id ?: 0

    val (token, tokenType) = currentTokenTypeStudentId

    LaunchedEffect(currentTokenTypeStudentId) {
        if (token.isNotEmpty() && tokenType.isNotEmpty() && studentId != 0) {
            viewModel.getExams(
                token = getToken(currentTokenTypeStudentId),
                tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                kelasId = currentClass?.kelasId ?: 0,
                isGrouBy = 1,
                type = ExamScreenType.LIST
            )
        }
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                title = stringResource(com.dailyapps.feature.score.R.string.ujian),
                onClickBack = { navController.popBackStack() },
                elevation = 1.dp
            ) {}
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (examScreenState) {
                is ExamScreenState.Success -> {
                    (examScreenState as ExamScreenState.Success).exams.let { exam ->
                        if (exam.isNotEmpty()) ExamList(exam, navController)
                        else EmptyList()
                    }
                }

                is ExamScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LoadingUi(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is ExamScreenState.Error -> {
                    (examScreenState as ExamScreenState.Error).message.let { msg ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            ErrorUi(message = msg, onButtonClick = {
                                viewModel.getExams(
                                    token = getToken(currentTokenTypeStudentId),
                                    tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                                    kelasId = currentClass?.kelasId ?: 0,
                                    isGrouBy = 1,
                                    type = ExamScreenType.LIST
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
fun ExamList(data: List<Exam>, navController: NavHostController) {
    var selected by remember {
        mutableIntStateOf(0)
    }
    if (data.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { exam ->
                ItemExam(
                    data = exam,
                    onValueChange = { selected = exam.id ?: 0 },
                    selected = selected == exam.id,
                    onItemClick = {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navController.navigate("${NavRoute.detailExamScreen}/${exam.tahunAjaranSemester?.semester}/${exam.ujian}")
                        }, 200)
                    })
            }
        }
    }
}

/*
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScoreScreenPreview() {
    val navHostController = rememberNavController()
    ListExamPage(navHostController)
}*/
