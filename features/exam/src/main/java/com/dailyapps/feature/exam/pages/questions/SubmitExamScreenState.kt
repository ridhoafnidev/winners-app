package com.dailyapps.feature.exam.pages.questions

import com.dailyapps.entity.SubmitExamResponse

sealed class SubmitExamScreenState {
    class Success(val exams: List<SubmitExamResponse>): SubmitExamScreenState()
    class Error(val message: String): SubmitExamScreenState()
    object Loading: SubmitExamScreenState()
}