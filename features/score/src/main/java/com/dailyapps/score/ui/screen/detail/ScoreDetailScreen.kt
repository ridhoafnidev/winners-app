package com.dailyapps.score.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.dailyapps.common.components.TextFieldDropdown
import com.dailyapps.common.components.tablayout.TabLayout
import com.dailyapps.common.components.tablayout.TabType
import com.dailyapps.entity.SchoolYear
import com.dailyapps.score.R
import com.dailyapps.score.ui.screen.ScoreScreenState
import com.dailyapps.score.ui.screen.ScoreViewModel
import com.dailyapps.score.ui.screen.detail.page.TabTaskScreen

@Composable
fun ScoreDetailScreen(
    subjectId: Int,
    navController: NavHostController,
    viewModel: ScoreViewModel
) {

    val menus = listOf(
        stringResource(R.string.tugas),
        stringResource(R.string.ulangan_harian),
        /*stringResource(R.string.uas),
        stringResource(R.string.uts)*/
    )

    var filterTask by rememberSaveable { mutableStateOf("") }
    var filterDailyTest by rememberSaveable { mutableStateOf("") }
   /* var filterUas by rememberSaveable { mutableStateOf("") }
    var filterUts by rememberSaveable { mutableStateOf("") }*/
    var matpel by rememberSaveable { mutableStateOf("-") }

    val scoreTaskResult by viewModel.scoreTaskScreenState.collectAsState()
    val scoreDailyTaskResult by viewModel.scoreDailyTestScreenState.collectAsState()
   /* val scoreUasResult by viewModel.scoreUasScreenState.collectAsState()
    val scoreUtsResult by viewModel.scoreUtsScreenState.collectAsState()*/

    val currentTokenTypeStudentId by viewModel.currentTokenTypeStudentId.collectAsState()
    val masterSchoolYear by viewModel.masterSchoolYear.collectAsState()
    val masterClassRoom by viewModel.masterClassRoom.collectAsState()
    val masterClassYears by viewModel.masterClassYears.collectAsState()
    val currentClass by viewModel.currentClass.collectAsState()

    val currentSchoolYear by lazy {
        masterSchoolYear.first()
    }

    val studentId = currentClass?.id ?: 0

    val (token, tokenType) = currentTokenTypeStudentId

    LaunchedEffect(currentTokenTypeStudentId) {
        if(token.isNotEmpty() && tokenType.isNotEmpty() && studentId != 0 && masterSchoolYear.isNotEmpty()) {
            filterTask = filterFormat(
                currentClass?.namaKelas ?: "",
                currentSchoolYear.semester ?: "",
                currentSchoolYear.tahunAjaran ?: ""
            )
            filterDailyTest = filterFormat(
                currentClass?.namaKelas ?: "",
                currentSchoolYear.semester ?: "",
                currentSchoolYear.tahunAjaran ?: ""
            )
            /*filterUas = filterFormat(
                currentClass?.namaKelas ?: "",
                currentSchoolYear.semester ?: "",
                currentSchoolYear.tahunAjaran ?: ""
            )
            filterUts = filterFormat(
                currentClass?.namaKelas ?: "",
                currentSchoolYear.semester ?: "",
                currentSchoolYear.tahunAjaran ?: ""
            )*/
            viewModel.getScoreStudent(
                token = getToken(currentTokenTypeStudentId),
                siswaId = studentId,
                tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                matpelId = subjectId,
                jenisNilaiId = ScoreType.TASK.type,
                kelasId = currentClass?.kelasId ?: 0,
                type = ScoreType.TASK
            )
            viewModel.getScoreStudent(
                token = getToken(currentTokenTypeStudentId),
                siswaId = studentId,
                tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                matpelId = subjectId,
                jenisNilaiId = ScoreType.DAILY_TEST.type,
                kelasId = currentClass?.kelasId ?: 0,
                type = ScoreType.DAILY_TEST
            )
            /*viewModel.getScoreStudent(
                token = getToken(currentTokenTypeStudentId),
                siswaId = studentId,
                tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                matpelId = subjectId,
                jenisNilaiId = ScoreType.UAS.type,
                kelasId = currentClass?.kelasId ?: 0,
                type = ScoreType.UAS
            )*/
            /*viewModel.getScoreStudent(
                token = getToken(currentTokenTypeStudentId),
                siswaId = studentId,
                tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                matpelId = subjectId,
                jenisNilaiId = ScoreType.UTS.type,
                kelasId = currentClass?.kelasId ?: 0,
                type = ScoreType.UTS
            )*/
        }
    }

    LaunchedEffect(masterClassRoom) {
        if (masterClassRoom.isNotEmpty() && masterSchoolYear.isNotEmpty()) {
            viewModel.getMasterClassYears(masterClassRoom, masterSchoolYear)
        }
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                title = stringResource(R.string.label_nilai),
                subTitle = matpel,
                onClickBack = { navController.popBackStack() },
                elevation = 1.dp
            ) {}
        }
    ) { contentPadding ->
        TabLayout(
            modifier = Modifier.padding(contentPadding),
            menus = menus,
            tabType = TabType.HISTORY,
            contentOne = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
                    text = filterTask,
                    label = stringResource(R.string.text_filter_class_school_year),
                    itemsDropdown = masterClassYears.map { helper ->
                        filterFormat(
                            helper.dataClass,
                            helper.dataSem,
                            helper.dataYear,
                        )
                    },
                    onValueChange = { value ->
                        filterTask = value

                        val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, value)

                        viewModel.getScoreStudent(
                            token = getToken(currentTokenTypeStudentId),
                            siswaId = studentId,
                            tahunAjaranId = yearId,
                            matpelId = subjectId,
                            jenisNilaiId = ScoreType.TASK.type,
                            kelasId = classId,
                            type = ScoreType.TASK
                        )

                    }
                )
                when(scoreTaskResult) {
                    is ScoreScreenState.Success -> {
                        (scoreTaskResult as ScoreScreenState.Success).score.let { score ->
                            if (score.isNotEmpty()) {
                                matpel = score.first().matpel?.matpel ?: "-"
                                TabTaskScreen(score)
                            }
                            else EmptyList()
                        }
                    }
                    is ScoreScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is ScoreScreenState.Error -> {
                        (scoreTaskResult as ScoreScreenState.Error).message.let { msg ->
                            val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, filterTask)
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    viewModel.getScoreStudent(
                                        token = getToken(currentTokenTypeStudentId),
                                        siswaId = studentId,
                                        tahunAjaranId = yearId,
                                        matpelId = subjectId,
                                        jenisNilaiId = ScoreType.TASK.type,
                                        kelasId = classId,
                                        type = ScoreType.TASK
                                    )
                                })
                            }
                        }
                    }
                }
            },
            contentTwo = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
                    text = filterDailyTest,
                    label = stringResource(R.string.text_filter_class_school_year),
                    itemsDropdown = masterClassYears.map { helper ->
                        filterFormat(
                            helper.dataClass,
                            helper.dataSem,
                            helper.dataYear,
                        )
                    },
                    onValueChange = { value ->
                        filterDailyTest = value

                        val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, value)

                        viewModel.getScoreStudent(
                            token = getToken(currentTokenTypeStudentId),
                            siswaId = studentId,
                            tahunAjaranId = yearId,
                            matpelId = subjectId,
                            jenisNilaiId = ScoreType.DAILY_TEST.type,
                            kelasId = classId,
                            type = ScoreType.DAILY_TEST
                        )

                    }
                )
                when(scoreDailyTaskResult) {
                    is ScoreScreenState.Success -> {
                        (scoreDailyTaskResult as ScoreScreenState.Success).score.let { score ->
                            if (score.isNotEmpty()) {
                                matpel = score.first().matpel?.matpel ?: "-"
                                TabTaskScreen(score)
                            }
                            else EmptyList()
                        }
                    }
                    is ScoreScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is ScoreScreenState.Error -> {
                        (scoreDailyTaskResult as ScoreScreenState.Error).message.let { msg ->
                            val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, filterDailyTest)
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    viewModel.getScoreStudent(
                                        token = getToken(currentTokenTypeStudentId),
                                        siswaId = studentId,
                                        tahunAjaranId = yearId, //
                                        matpelId = subjectId,
                                        jenisNilaiId = ScoreType.DAILY_TEST.type,
                                        kelasId = classId, //
                                        type = ScoreType.DAILY_TEST
                                    )
                                })
                            }
                        }
                    }
                }
            },
            /*contentThree = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
                    text = filterUas,
                    label = stringResource(R.string.text_filter_class_school_year),
                    itemsDropdown = masterClassYears.map { helper ->
                        filterFormat(
                            helper.dataClass,
                            helper.dataSem,
                            helper.dataYear,
                        )
                    },
                    onValueChange = { value ->
                        filterUas = value

                        val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, value)

                        viewModel.getScoreStudent(
                            token = getToken(currentTokenTypeStudentId),
                            siswaId = studentId,
                            tahunAjaranId = yearId,
                            matpelId = subjectId,
                            jenisNilaiId = ScoreType.UAS.type,
                            kelasId = classId,
                            type = ScoreType.UAS
                        )

                    }
                )
                when(scoreUasResult) {
                    is ScoreScreenState.Success -> {
                        (scoreUasResult as ScoreScreenState.Success).score.let { score ->
                            if (score.isNotEmpty()) {
                                matpel = score.first().matpel?.matpel ?: "-"
                                TabTaskScreen(score)
                            }
                            else EmptyList()
                        }
                    }
                    is ScoreScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is ScoreScreenState.Error -> {
                        (scoreUasResult as ScoreScreenState.Error).message.let { msg ->
                            val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, filterUas)
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    viewModel.getScoreStudent(
                                        token = getToken(currentTokenTypeStudentId),
                                        siswaId = studentId,
                                        tahunAjaranId = yearId, //
                                        matpelId = subjectId,
                                        jenisNilaiId = ScoreType.UAS.type,
                                        kelasId = classId, //
                                        type = ScoreType.UAS
                                    )
                                })
                            }
                        }
                    }
                }
            },
            contentFour = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
                    text = filterUts,
                    label = stringResource(R.string.text_filter_class_school_year),
                    itemsDropdown = masterClassYears.map { helper ->
                        filterFormat(
                            helper.dataClass,
                            helper.dataSem,
                            helper.dataYear,
                        )
                    },
                    onValueChange = { value ->
                        filterUts = value

                        val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, value)

                        viewModel.getScoreStudent(
                            token = getToken(currentTokenTypeStudentId),
                            siswaId = studentId,
                            tahunAjaranId = yearId,
                            matpelId = subjectId,
                            jenisNilaiId = ScoreType.UTS.type,
                            kelasId = classId,
                            type = ScoreType.UTS
                        )

                    }
                )
                when(scoreUtsResult) {
                    is ScoreScreenState.Success -> {
                        (scoreUtsResult as ScoreScreenState.Success).score.let { score ->
                            if (score.isNotEmpty()) {
                                matpel = score.first().matpel?.matpel ?: "-"
                                TabTaskScreen(score)
                            }
                            else EmptyList()
                        }
                    }
                    is ScoreScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is ScoreScreenState.Error -> {
                        (scoreUtsResult as ScoreScreenState.Error).message.let { msg ->
                            val (classId, yearId) = viewModel.spliteScoreFiltered(masterClassYears, filterUts)
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    viewModel.getScoreStudent(
                                        token = getToken(currentTokenTypeStudentId),
                                        siswaId = studentId,
                                        tahunAjaranId = yearId, //
                                        matpelId = subjectId,
                                        jenisNilaiId = ScoreType.UTS.type,
                                        kelasId = classId, //
                                        type = ScoreType.UTS
                                    )
                                })
                            }
                        }
                    }
                }
            }*/
        )
    }
}

fun getCurrentSchoolYear(year: SchoolYear) =
    year.id ?: 0

fun filterFormat(className: String, semester: String, schoolYear: String) =
    "Kelas $className - Sem. $semester - TA $schoolYear"

fun getToken(currentToken: Pair<String, String>) =
    "${currentToken.second} ${currentToken.first}"

enum class ScoreType(val type: Int){
    DAILY_TEST(1),
    TASK(2),
   /* UTS(3),
    UAS(4),*/
    OTHER(5)
}

/*
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
fun ScoreDetailScreenPreview() {
    val navHostController = rememberNavController()
    ScoreDetailScreen(0, navHostController)
}*/
