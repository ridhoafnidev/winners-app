package com.dailyapps.data.remote.datasource

import com.dailyapps.apirequest.SubmitExamApiRequest
import com.dailyapps.data.mapper.ExamQuestionMapper
import com.dailyapps.data.mapper.ExamRulesMapper
import com.dailyapps.data.mapper.SubmitExamMapper
import com.dailyapps.data.mapper.SuspendStudentExamsMapper
import com.dailyapps.data.remote.service.ExamQuestionService
import com.dailyapps.data.utils.DataMapper.toDomainSubmitExamRequest
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.ExamQuestionUseCase
import com.dailyapps.domain.usecase.ExamRulesUseCase
import com.dailyapps.domain.usecase.SubmitExamUseCase
import com.dailyapps.domain.usecase.ChangeStudentExamsStatusUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ExamQuestion
import com.dailyapps.entity.ExamRules
import com.dailyapps.entity.SubmitExamResponse
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExamQuestionRemoteDataSource @Inject constructor(
    private val service: ExamQuestionService,
    private val examQuestionMapper: ExamQuestionMapper,
    private val submitExamMapper: SubmitExamMapper,
    private val examRulesMapper: ExamRulesMapper,
    private val suspendStudentExamsMapper: SuspendStudentExamsMapper
) {

    suspend fun getExamQuestion(params: ExamQuestionUseCase.Params): Flow<Resource<List<ExamQuestion>>> {
        return mapFromApiResponse(
            result = apiCall {
                service.getExamQuestion(
                    token = params.token,
                    ujianId = params.ujianId
                )
            },
            mapper = examQuestionMapper
        )
    }

    suspend fun submitExam(params: SubmitExamUseCase.Params): Flow<Resource<List<SubmitExamResponse>>> {
        val submitExamApiRequest = SubmitExamApiRequest(
            jawaban = params.submitExamRequests.map { it.toDomainSubmitExamRequest() }
        )
        return mapFromApiResponse(
            result = apiCall {
                service.submitExam(
                    token = params.token,
                    submitExamApiRequest = submitExamApiRequest
                )
            },
            mapper = submitExamMapper
        )
    }

    suspend fun getExamRules(params: ExamRulesUseCase.Params): Flow<Resource<List<ExamRules>>> {
        return mapFromApiResponse(
            result = apiCall {
                service.getExamRules(token = params.token)
            },
            mapper = examRulesMapper
        )
    }

    suspend fun changeStudentExamsStatus(params: ChangeStudentExamsStatusUseCase.Params): Flow<Resource<ChangeStudentExamsStateResponse>> {
        return mapFromApiResponse(
            result = apiCall {
                service.changeStudentExamsStatus(
                    token = params.token,
                    studentId = params.studentId,
                    examId = params.examId,
                    status = params.status
                )
            },
            mapper = suspendStudentExamsMapper
        )
    }

}