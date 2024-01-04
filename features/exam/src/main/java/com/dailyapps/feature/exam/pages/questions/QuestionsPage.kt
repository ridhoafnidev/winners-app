package com.dailyapps.feature.exam.pages.questions

import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dailyapps.common.Danger600
import com.dailyapps.common.Neutral900
import com.dailyapps.common.components.BaseButton
import com.dailyapps.common.components.BaseDetailImageDialog
import com.dailyapps.common.components.BaseDialog
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.DisposableEffectWithLifeCycle
import com.dailyapps.common.components.ErrorUi
import com.dailyapps.common.components.ExamAppBar
import com.dailyapps.common.components.LoadingUi
import com.dailyapps.common.utils.NavRoute
import com.dailyapps.common.utils.convertHtmlToString
import com.dailyapps.common.utils.toTimeFormat
import com.dailyapps.entity.ExamQuestion
import com.dailyapps.entity.ExamRules
import com.dailyapps.entity.SubmitExamRequest
import com.dailyapps.feature.exam.components.ItemExamQuestions
import com.dailyapps.feature.score.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@ExperimentalMaterial3Api
@Composable
fun ExamQuestionPage(
    navController: NavHostController,
    examViewModel: ExamQuestionViewModel,
    token: String,
    ujianId: Int,
    siswaId: Int,
    ujianEnd: LocalDateTime,
    matpel: String
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val examQuestionScreenState by examViewModel.examQuestionScreenState.collectAsState()
    val examRules by examViewModel.examRules.collectAsState()
    val submitExamState by examViewModel.submitExamState.collectAsState()
    val submitExamRequests by examViewModel.submitExamRequests.collectAsState()
    val finishStudentExamsResponse by examViewModel.finishStudentExams.collectAsState()
    val suspendStudentExamsResponse by examViewModel.suspendStudentExams.collectAsState()

    val buttonFinishExamLoadingState = remember(key1 = submitExamState) {
        submitExamState == SubmitExamState.Loading
    }

    val buttonFinishExamEnableState by examViewModel.buttonFinishedExamEnabled.collectAsState(
        initial = false
    )

    val examHour by examViewModel.examHour.collectAsState(initial = 0)
    val examMinutes by examViewModel.examMinutes.collectAsState(initial = 0)
    val examSeconds by examViewModel.examSeconds.collectAsState(initial = 0)

    val isExamEmpty by examViewModel.isExamQuestionEmpty.collectAsState()
    val examTimeOut by examViewModel.examTimeOut.collectAsState()
    val examViolationState by examViewModel.examViolationState.collectAsState()
    val examFinishState by examViewModel.examFinishState.collectAsState()

    val topAppBarSubtitleColor = remember(examMinutes) {
        if (examMinutes > 10) {
            Neutral900
        } else {
            Danger600
        }
    }

    var dialogExamAgreementState by remember { mutableStateOf(false) }
    var dialogFinishExamState by remember { mutableStateOf(false) }
    var dialogExamViolationState by remember { mutableStateOf(false) }
    var selectedExamImage by remember { mutableStateOf<String?>(null) }

    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(NavRoute.homeScreen) {
            popUpTo(NavRoute.homeScreen) {
                inclusive = true
            }
        }
    }

    val getExamQuestion = {
        examViewModel.getExamQuestion(token, ujianId, siswaId)
    }

    val finishStudentExamRequest: () -> Unit = {
        examViewModel.finishStudentExams(token, siswaId, ujianId)
    }

    val suspendStudentExamRequest: () -> Unit = {
        examViewModel.suspendStudentExams(token, siswaId, ujianId)
    }

    LaunchedEffect(key1 = Unit) {
        getExamQuestion()
    }

    LaunchedEffect(key1 = examQuestionScreenState) {
        if (examQuestionScreenState is ExamQuestionScreenState.Success) {
            examViewModel.getExamRules(token)
        }
    }

    LaunchedEffect(key1 = examRules) {
        if (examRules is ExamQuestionScreenState.Success) {
            dialogExamAgreementState = true
        }
    }

    LaunchedEffect(key1 = examViolationState) {
        if (examViolationState) {
            examViewModel.cancelExamCountdownTimer()
        }
    }

    LaunchedEffect(key1 = examTimeOut) {
        if (examTimeOut) {
            selectedExamImage = null
        }
    }

    LaunchedEffect(key1 = finishStudentExamsResponse) {
        if (finishStudentExamsResponse is ExamQuestionScreenState.Success) {
            examViewModel.submitExam(token)
        }
    }

    DisposableEffectWithLifeCycle(
        onStop = {
            if (!examTimeOut && !examFinishState) {
                examViewModel.startViolationCountdownTimer(token, siswaId, ujianId)
            }
        },
        onResume = fun() {
            examViewModel.cancelViolationCountdownTimer()
            if (!examViolationState) return
            scope.launch {
                delay(500L)
                dialogExamViolationState = true
            }
        }
    )

    val onDoExamButtonClicked = {
        dialogExamAgreementState = false
        examViewModel.startExamCountdownTimer(ujianEnd)
    }

    val showFinishExamDialogRequest: () -> Unit = {
        dialogFinishExamState = true
    }

    val onDismissDialogRequest: () -> Unit = {
        dialogFinishExamState = false
    }

    val onCancelButtonDialogClicked: () -> Unit = {
        dialogFinishExamState = false
    }

    val onConfirmButtonDialogClicked: () -> Unit = fun() {
        dialogFinishExamState = false

        if (buttonFinishExamEnableState) {
            examViewModel.updateExamTimeOutState(false)
            examViewModel.updateSubmitExamState(SubmitExamState.Loading)
            finishStudentExamRequest()
        } else {
            if (examTimeOut) {
                Toast.makeText(
                    context,
                    "Waktu Ujian anda habis, namun jawaban anda masih kosong. Sehingga ujian tidak bisa disubmit",
                    Toast.LENGTH_SHORT
                ).show()
                navigateToHomeScreen()
                return
            }

            Toast.makeText(
                context,
                "Belum ada ujian yang dijawab, silahkan jawaban ujian terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val submitExamOrBackToPreviousScreen: () -> Unit = {
        if (!isExamEmpty) {
            showFinishExamDialogRequest()
        } else {
            navController.popBackStack()
        }
    }

    if (dialogExamAgreementState) {
        val examRule = (examRules as ExamQuestionScreenState.Success<ExamRules>).exams
        BaseDialog(
            title = examRule.title ?: "",
            description = examRule.description ?: "",
            onConfirmClicked = onDoExamButtonClicked,
            confirmButtonText = "Kerjakan ujian",
            cancelButtonVisible = false
        )
    }

    if (dialogFinishExamState) {
        BaseDialog(
            title = stringResource(R.string.title_dialog_finish_exam),
            description = stringResource(R.string.desc_dialog_finish_exam),
            onDismissRequest = onDismissDialogRequest,
            onCancelRequest = onCancelButtonDialogClicked,
            onConfirmClicked = onConfirmButtonDialogClicked,
            confirmButtonText = "Kirim Ujian",
            cancelButtonText = "Batal"
        )
    }

    if (examTimeOut) {
        BaseDialog(
            title = "Waktu ujian sudah habis!",
            description = "Silahkan klik tombol selesaikan ujian, untuk mengakhir sesi ujian kali ini!",
            onConfirmClicked = showFinishExamDialogRequest,
            confirmButtonText = "Kirim Ujian",
            cancelButtonVisible = false
        )
    }

    if (submitExamState == SubmitExamState.Success) {
        BaseDialog(
            title = "Ujian berhasil disubmit!",
            description = "Silahkan klik tombol kembali ke halaman home, untuk meninggalkan halaman ujian!",
            onConfirmClicked = navigateToHomeScreen,
            confirmButtonText = "Kembali kehalaman home",
            cancelButtonVisible = false
        )
    }

    if (submitExamState == SubmitExamState.Error) {
        BaseDialog(
            title = "Ujian gagal disubmit!",
            description = "Silahkan klik tombol coba lagi, untuk mensubmit ujian kembali!",
            onConfirmClicked = onConfirmButtonDialogClicked,
            confirmButtonText = "Coba lagi",
            cancelButtonVisible = false
        )
    }

    if (dialogExamViolationState) {
        BaseDialog(
            title = "Pelanggaran ujian!",
            description = "Anda telah melakukan pelanggaran didalam ujian!",
            onConfirmClicked = navigateToHomeScreen,
            confirmButtonText = "Pergi ke halaman home",
            cancelButtonVisible = false
        )
    }

    if (finishStudentExamsResponse is ExamQuestionScreenState.Error) {
        BaseDialog(
            title = "Terjadi kesalahan!",
            description = "Tekan tombol coba lagi, untuk melanjutkan!",
            onConfirmClicked = finishStudentExamRequest,
            confirmButtonText = "Coba lagi",
            cancelButtonVisible = false
        )
    }

    if (suspendStudentExamsResponse is ExamQuestionScreenState.Error) {
        BaseDialog(
            title = "Terjadi kesalahan!",
            description = "Tekan tombol coba lagi, untuk melanjutkan!",
            onConfirmClicked = suspendStudentExamRequest,
            confirmButtonText = "Coba lagi",
            cancelButtonVisible = false
        )
    }

    if (selectedExamImage != null) {
        BaseDetailImageDialog(
            imageUrl = selectedExamImage!!,
            onDismissRequest = { selectedExamImage = null }
        )
    }

    BackHandler(!dialogFinishExamState) {
        submitExamOrBackToPreviousScreen()
    }

    Scaffold(
        topBar = {
            ExamAppBar(
                title = matpel.uppercase(),
                subtitle = "Waktu ujian : ${examHour.toTimeFormat()}:${examMinutes.toTimeFormat()}:${examSeconds.toTimeFormat()}".uppercase(),
                navigationBackClicked = submitExamOrBackToPreviousScreen,
                subtitleColor = topAppBarSubtitleColor
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (examQuestionScreenState) {
                ExamQuestionScreenState.Loading -> {
                    LoadingUi(modifier = Modifier.fillMaxSize())
                }

                is ExamQuestionScreenState.Error -> {
                    ErrorUi(
                        message = (examQuestionScreenState as ExamQuestionScreenState.Error).message,
                        onButtonClick = getExamQuestion
                    )
                }

                is ExamQuestionScreenState.Success -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ExamQuestionList(
                            examQuestions = (examQuestionScreenState as ExamQuestionScreenState.Success).exams,
                            submitExamRequests = submitExamRequests,
                            onAnswerChanged = examViewModel::updateSubmitExamRequest,
                            onFinishExamClicked = showFinishExamDialogRequest,
                            buttonFinishExamEnableState = buttonFinishExamEnableState,
                            buttonFinishExamLoadingState = buttonFinishExamLoadingState,
                            onImageQuestionClicked = { selectedExamImage = it }
                        )
                        if (buttonFinishExamLoadingState) {
                            LoadingUi(modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExamQuestionList(
    examQuestions: List<ExamQuestion>,
    submitExamRequests: List<SubmitExamRequest>,
    onAnswerChanged: (SubmitExamRequest) -> Unit = {},
    onFinishExamClicked: () -> Unit = {},
    buttonFinishExamEnableState: Boolean = false,
    buttonFinishExamLoadingState: Boolean = false,
    onImageQuestionClicked: (String) -> Unit = {},
) {
    if (examQuestions.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(items = examQuestions) { index, exam ->
                ItemExamQuestions(
                    examQuestion = exam,
                    questionNumber = index + 1,
                    submitExamRequest = submitExamRequests[index],
                    onAnswerChanged = {
                        val submitExamRequest = submitExamRequests[index].copy(
                            jawaban = it
                        )
                        onAnswerChanged(submitExamRequest)
                    },
                    onImageQuestionClicked = onImageQuestionClicked
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                BaseButton(
                    text = "Selesaikan Ujian",
                    enabled = buttonFinishExamEnableState,
                    isLoading = buttonFinishExamLoadingState,
                    onClick = onFinishExamClicked,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Daftar ujian kosong!")
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TestPagePreview() {
//    val navHostController = rememberNavController()
//    ExamQuestionPage(navHostController)
    MaterialTheme {
        androidx.compose.material3.Surface {
            val htmlText =
                "<p>f(x) = x&sup;2; + 2 dan g(x) = 2x + 5 dan f(x) = g(x) maka x adalah&hellip;</p>".convertHtmlToString()
            BaseText(text = htmlText)
        }
    }
}