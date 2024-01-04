package com.dailyapps.feature.exam.pages.questions

sealed class ExamQuestionScreenState<out T> {
    class Success<T>(val exams: T): ExamQuestionScreenState<T>()
    class Error(val message: String): ExamQuestionScreenState<Nothing>()
    object Loading: ExamQuestionScreenState<Nothing>()
}
