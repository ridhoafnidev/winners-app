package com.dailyapps.feature.exam

import com.dailyapps.entity.Exam

sealed class ExamScreenState {
    class Success(val exams: List<Exam>): ExamScreenState()
    class Error(val message: String): ExamScreenState()
    object Loading: ExamScreenState()
}