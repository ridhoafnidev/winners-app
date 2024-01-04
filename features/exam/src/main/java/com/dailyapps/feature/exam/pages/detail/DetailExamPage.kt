package com.dailyapps.feature.exam.pages.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dailyapps.common.EmptyList
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.components.BaseDialog
import com.dailyapps.common.components.ErrorUi
import com.dailyapps.common.components.LoadingUi
import com.dailyapps.common.utils.NavRoute
import com.dailyapps.common.utils.getCurrentWibLocalDateTime
import com.dailyapps.common.utils.toLocalDateTime
import com.dailyapps.entity.Exam
import com.dailyapps.feature.exam.ExamScreenState
import com.dailyapps.feature.exam.ExamViewModel
import com.dailyapps.feature.exam.components.ItemExamDetail
import com.dailyapps.feature.exam.pages.list.getCurrentSchoolYear
import com.dailyapps.feature.exam.pages.list.getToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailExamPage(
    semester: String,
    jenisUjian: String,
    navController: NavHostController,
    viewModel: ExamViewModel
) {

    val coroutineScope = rememberCoroutineScope()

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    var message by remember { mutableStateOf("") }

    val examDetailScreenState by viewModel.examDetailScreenState.collectAsState()
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
                type = ExamScreenType.DETAIL
            )
        }
    }

    val showExamDialog: (String) -> Unit = {
        message = it
    }

    val hideExamDialog: () -> Unit = {
        message = ""
    }

    val onDetailExamClicked = fun(exam: Exam) {
        if (exam.start) {
            if (exam.finish) {
                showExamDialog("Maaf, sesi ujiah sudah selesai!")
                return
            }
            val isExamTimeFinish = if (exam.ujianEnd != null) {
                val ujianEndLocalDateTime = exam.ujianEnd!!.toLocalDateTime()
                val currentTime = getCurrentWibLocalDateTime()
                val isExamTimeFinish = currentTime > ujianEndLocalDateTime
                isExamTimeFinish
            } else true
            if (isExamTimeFinish) {
                showExamDialog("Maaf, sesi ujiah sudah selesai!")
                return
            }
            if (exam.studentCanAttemptExam) {
                coroutineScope.launch {
                    delay(200L)
                    val authToken = getToken(currentTokenTypeStudentId)
                    val examToken = exam.token
                    val ujianId = exam.id
                    val siswaId = studentId
                    val ujianEnd = exam.ujianEnd
                    val matpel = exam.matpel?.matpel
                    navController.navigate("${NavRoute.tokeExamScreen}/$examToken/$authToken/$ujianId/$siswaId/$ujianEnd/$matpel")
                }
            }
            else if (exam.isStudentFinishTheExam) {
                showExamDialog("Kamu telah menyelesaikan ujian!")
            }
            else if (exam.isStudentSuspended) {
                showExamDialog("Kamu telah disuspend dari ujian!")
            }
            else if (exam.isStudentAlreadyRedeemToken) {
                showExamDialog("Kamu telah memasukan token ujian!")
            }
        }
        else {
            showExamDialog("Maaf, ujian yang belum di izin kan guru, tidak bisa diakses dulu!")
        }
    }

    if (message.isNotEmpty()) {
        BaseDialog(
            title = "Pemberitahuan",
            description = message,
            onConfirmClicked = hideExamDialog,
            confirmButtonText = "Tutup",
            cancelButtonVisible = false
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            BaseAppBar(
                title = jenisUjian,
                subTitle = "Semester $semester",
                onClickBack = { navController.popBackStack() },
                elevation = 1.dp
            ) {
            }
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (examDetailScreenState) {
                is ExamScreenState.Success -> {
                    (examDetailScreenState as ExamScreenState.Success).exams.let { exam ->
                        if (exam.isNotEmpty()) {
                            DetailExamList(exam, onDetailExamClicked)
                         }else EmptyList()
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
                    (examDetailScreenState as ExamScreenState.Error).message.let { msg ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            ErrorUi(message = msg, onButtonClick = {
                                viewModel.getExams(
                                    token = getToken(currentTokenTypeStudentId),
                                    tahunAjaranId = getCurrentSchoolYear(currentSchoolYear),
                                    kelasId = currentClass?.kelasId ?: 0,
                                    type = ExamScreenType.DETAIL
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
fun DetailExamList(
    data: List<Exam>,
    onDetailExamClicked: (Exam) -> Unit = {}
) {
    var selected by remember {
        mutableIntStateOf(0)
    }

    if (data.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { exam ->
                ItemExamDetail(
                    data = exam,
                    onValueChange = { selected = exam.id ?: 0 },
                    selected = selected == exam.id,
                    onItemClick = onDetailExamClicked
                )
            }
        }
    }
}

/*@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailExamPagePreview() {
    val navHostController = rememberNavController()
    DetailExamPage(navHostController)
}*/

enum class ExamScreenType(type: Int) {
    LIST(1), DETAIL(2)
}