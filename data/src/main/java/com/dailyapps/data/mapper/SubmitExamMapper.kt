package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.SubmitExamApiResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.SubmitExamResponse
import javax.inject.Inject

class SubmitExamMapper @Inject constructor() : Mapper<BaseResponse<SubmitExamApiResponse>, List<SubmitExamResponse>> {

    override fun mapFromApiResponse(type: BaseResponse<SubmitExamApiResponse>): List<SubmitExamResponse> {
        val data = type.data
        return data.jawaban?.map { submitExamItem ->
            SubmitExamResponse(
                updatedAt = submitExamItem.updatedAt,
                nilai = submitExamItem.nilai,
                createdAt = submitExamItem.createdAt,
                jawaban = submitExamItem.jawaban,
                id = submitExamItem.id,
                soalId = submitExamItem.soalId,
                siswaId = submitExamItem.siswaId
            )
        } ?: emptyList()
    }

}