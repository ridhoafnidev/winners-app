package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ExamQuestionApiResponse
import com.dailyapps.data.utils.DataMapper.toDomainExamItem
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.ExamQuestion
import javax.inject.Inject

class ExamQuestionMapper @Inject constructor(): Mapper<BaseResponse<List<ExamQuestionApiResponse>>, List<ExamQuestion>> {

    override fun mapFromApiResponse(type: BaseResponse<List<ExamQuestionApiResponse>>): List<ExamQuestion> {
        return type.data.map { examQuestionItem ->
            ExamQuestion(
                examItem = examQuestionItem.examItem?.toDomainExamItem(),
                createdAt = examQuestionItem.createdAt,
                ujianId = examQuestionItem.ujianId,
                deletedAt = examQuestionItem.deletedAt,
                jenisSoal = examQuestionItem.jenisSoal,
                kunciJawaban = examQuestionItem.kunciJawaban,
                opsiB = examQuestionItem.opsiB,
                opsiC = examQuestionItem.opsiC,
                imagesSoal = examQuestionItem.imagesSoal,
                opsiA = examQuestionItem.opsiA,
                updatedAt = examQuestionItem.updatedAt,
                opsiD = examQuestionItem.opsiD,
                opsiE = examQuestionItem.opsiE,
                id = examQuestionItem.id,
                bobotNilai = examQuestionItem.bobotNilai,
                textSoal = examQuestionItem.textSoal
            )
        }
    }
}