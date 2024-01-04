package com.dailyapps.feature.exam.pages.questions

import android.os.CountDownTimer
import androidx.lifecycle.viewModelScope
import com.dailyapps.common.utils.getCurrentWibLocalDateTime
import com.dailyapps.domain.usecase.ChangeStudentExamsStatusUseCase
import com.dailyapps.domain.usecase.ExamQuestionUseCase
import com.dailyapps.domain.usecase.ExamRulesUseCase
import com.dailyapps.domain.usecase.SubmitExamUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import com.dailyapps.entity.ExamQuestion
import com.dailyapps.entity.ExamRules
import com.dailyapps.entity.SubmitExamRequest
import com.dailyapps.feature.exam.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

enum class SubmitExamState {
    Idling,
    Loading,
    Success,
    Error
}

@HiltViewModel
class ExamQuestionViewModel @Inject constructor(
    private val examQuestionUseCase: ExamQuestionUseCase,
    private val submitExamUseCase: SubmitExamUseCase,
    private val examRulesUseCase: ExamRulesUseCase,
    private val changeStudentExamsStatusUseCase: ChangeStudentExamsStatusUseCase
) : BaseViewModel() {

    private val _examQuestionScreenState = MutableStateFlow<ExamQuestionScreenState<List<ExamQuestion>>>(ExamQuestionScreenState.Loading)
    internal val examQuestionScreenState get() = _examQuestionScreenState.asStateFlow()

    private val _submitExamState = MutableStateFlow(SubmitExamState.Idling)
    internal val submitExamState get() = _submitExamState.asStateFlow()

    private val _submitExamRequests = MutableStateFlow<List<SubmitExamRequest>>(emptyList())
    internal val submitExamRequests get() = _submitExamRequests.asStateFlow()

    private val _isExamQuestionEmpty = MutableStateFlow(false)
    internal val isExamQuestionEmpty get() = _isExamQuestionEmpty.asStateFlow()

    val examHour = MutableStateFlow(0L)
    val examMinutes = MutableStateFlow(0L)
    val examSeconds = MutableStateFlow(0L)

    private val _examTimeOut = MutableStateFlow(false)
    val examTimeOut get() = _examTimeOut.asStateFlow()

    private val _examViolationState = MutableStateFlow(false)
    val examViolationState get() = _examViolationState.asStateFlow()

    private val _examFinishState = MutableStateFlow(false)
    val examFinishState get() = _examFinishState.asStateFlow()

    internal val buttonFinishedExamEnabled get() = _submitExamRequests.map {
        it.all { submitExamRequests ->
            submitExamRequests.jawaban.isNotEmpty()
        }
    }


    private var examCountdownTimer: CountDownTimer? = null
    private var violationCountdownTimer: CountDownTimer? = null

    private val _examRules = MutableStateFlow<ExamQuestionScreenState<ExamRules>>(ExamQuestionScreenState.Loading)
    val examRules get() = _examRules.asStateFlow()

    private val _suspendStudentExams = MutableStateFlow<ExamQuestionScreenState<ChangeStudentExamsStateResponse>>(ExamQuestionScreenState.Loading)
    val suspendStudentExams get() = _suspendStudentExams.asStateFlow()

    private val _finishStudentExams = MutableStateFlow<ExamQuestionScreenState<ChangeStudentExamsStateResponse>>(ExamQuestionScreenState.Loading)
    val finishStudentExams get() = _finishStudentExams.asStateFlow()

    fun getExamQuestion(token: String, examId: Int, studentId: Int) {
        viewModelScope.launch {
            val examQuestionParams = ExamQuestionUseCase.Params(
                token = token,
                ujianId = examId
            )
            examQuestionUseCase.execute(examQuestionParams).collect { result ->
                when (result) {
                    Resource.Loading -> _examQuestionScreenState.value = ExamQuestionScreenState.Loading
                    is Resource.Error -> _examQuestionScreenState.value = ExamQuestionScreenState.Error(result.msg)
                    is Resource.Success -> {
                        _examQuestionScreenState.value = ExamQuestionScreenState.Success(result.data)
                        _isExamQuestionEmpty.value = result.data.isEmpty()
                        _submitExamRequests.value = result.data.map {
                            SubmitExamRequest(
                                soalId = it.id ?: 0,
                                siswaId = studentId
                            )
                        }
                    }
                }
            }
        }
    }

    fun getExamRules(token: String) {
        viewModelScope.launch {
            val examRulesParams = ExamRulesUseCase.Params(token = token)
            examRulesUseCase.execute(examRulesParams).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        val examRules = result.data.firstOrNull() ?: ExamRules(
                            title = "Peraturan",
                            description = "Anda tidak boleh mencontek"
                        )
                        _examRules.value = ExamQuestionScreenState.Success(examRules)
                    }
                }
            }
        }
    }

    fun submitExam(token: String) {
        viewModelScope.launch {
            val filteredNotEmptySubmitExamRequests = _submitExamRequests.value.filter { it.jawaban.isNotEmpty() }
            val submitExamParams = SubmitExamUseCase.Params(
                token = token,
                submitExamRequests = filteredNotEmptySubmitExamRequests
            )
            submitExamUseCase.execute(submitExamParams).collect { result ->
                when (result) {
                    Resource.Loading -> {
                        _submitExamState.value = SubmitExamState.Loading
                    }
                    is Resource.Error -> {
                        _submitExamState.value = SubmitExamState.Error
                    }
                    is Resource.Success -> {
                        _examFinishState.value = true
                        _submitExamState.value = SubmitExamState.Success
                        examCountdownTimer?.cancel()
                    }
                }
            }
        }
    }

    fun suspendStudentExams(token: String, studentId: Int, examId: Int) {
        viewModelScope.launch {
            val suspendStudentExamsParams = ChangeStudentExamsStatusUseCase.Params(
                token, studentId, examId, 1
            )
            changeStudentExamsStatusUseCase.execute(suspendStudentExamsParams).collect { result ->
                when (result) {
                    Resource.Loading -> _suspendStudentExams.value = ExamQuestionScreenState.Loading
                    is Resource.Error -> {
                        _examViolationState.value = false
                        _suspendStudentExams.value = ExamQuestionScreenState.Error(result.msg)
                    }
                    is Resource.Success -> {
                        _examViolationState.value = true
                        _suspendStudentExams.value = ExamQuestionScreenState.Success(result.data)
                    }
                }
            }
        }
    }

    fun finishStudentExams(token: String, studentId: Int, examId: Int) {
        viewModelScope.launch {
            val suspendStudentExamsParams = ChangeStudentExamsStatusUseCase.Params(
                token, studentId, examId, 0
            )
            changeStudentExamsStatusUseCase.execute(suspendStudentExamsParams).collect { result ->
                when (result) {
                    Resource.Loading -> _finishStudentExams.value = ExamQuestionScreenState.Loading
                    is Resource.Error -> _finishStudentExams.value = ExamQuestionScreenState.Error(result.msg)
                    is Resource.Success -> _finishStudentExams.value = ExamQuestionScreenState.Success(result.data)
                }
            }
        }
    }

    fun updateSubmitExamRequest(submitExamRequest: SubmitExamRequest) {
        _submitExamRequests.value = _submitExamRequests.value.map {
            if (submitExamRequest.soalId == it.soalId) {
                it.copy(
                    jawaban = submitExamRequest.jawaban
                )
            } else it
        }
    }

    fun startExamCountdownTimer(endExamTime: LocalDateTime, intervalInMillis: Long = 1000) {

        val currentWibLocalDateTime = getCurrentWibLocalDateTime()
        val currentTimeMinutes = (currentWibLocalDateTime.hour * 60) + currentWibLocalDateTime.minute
        val endTimeExamTimeHour = (endExamTime.hour * 60) + endExamTime.minute
        val remainingMinutesExamTime = (endTimeExamTimeHour - currentTimeMinutes).toLong()
        val totalDurationTimeMillis = remainingMinutesExamTime * 60000

        if (totalDurationTimeMillis < 0) _examTimeOut.value = true

        examCountdownTimer = object : CountDownTimer(totalDurationTimeMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / 3600000
                val minutes = (millisUntilFinished % 3600000) / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                examHour.value = hours
                examMinutes.value = minutes
                examSeconds.value = seconds
                if (hours == 0L && minutes == 0L && seconds == 0L) {
                    _examTimeOut.value = true
                    examCountdownTimer = null
                }
            }

            override fun onFinish() {
            }
        }.start()
    }

    fun startViolationCountdownTimer(
        token: String, studentId: Int, examId: Int
    ) {
        val maximalTimeViolations = 15000L // 15 seconds
        val intervalInMillis = 1000L // 1 seconds
        violationCountdownTimer = object : CountDownTimer(maximalTimeViolations, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                suspendStudentExams(token, studentId, examId)
                violationCountdownTimer = null
            }
        }.start()
    }

    fun updateExamTimeOutState(state: Boolean) {
        _examTimeOut.value = state
    }

    fun updateSubmitExamState(state: SubmitExamState) {
        _submitExamState.value = state
    }

    fun cancelExamCountdownTimer() {
        examCountdownTimer?.cancel()
        examCountdownTimer = null
    }

    fun cancelViolationCountdownTimer() {
        violationCountdownTimer?.cancel()
        violationCountdownTimer = null
    }

    override fun onCleared() {
        super.onCleared()
        examCountdownTimer = null
        violationCountdownTimer = null
    }

}
