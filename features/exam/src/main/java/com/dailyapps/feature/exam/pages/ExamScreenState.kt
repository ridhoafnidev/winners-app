package com.dailyapps.feature.exam.pages

import com.dailyapps.entity.ExamQuestion

sealed class ExamScreenState {
    class Success(val exams: List<ExamQuestion>): ExamScreenState()
    class Error(val message: String): ExamScreenState()
    object Loading: ExamScreenState()
}