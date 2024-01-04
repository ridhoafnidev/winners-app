package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ExamRulesResponse
import com.dailyapps.common.utils.convertHtmlToString
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.ExamRules
import javax.inject.Inject

class ExamRulesMapper @Inject constructor() : Mapper<BaseResponse<List<ExamRulesResponse>>, List<ExamRules>> {

    override fun mapFromApiResponse(type: BaseResponse<List<ExamRulesResponse>>): List<ExamRules> {
        val data = type.data
        return data.map { examRule ->
            ExamRules(
                id = examRule.id,
                title = examRule.title,
                description = examRule.description?.convertHtmlToString(),
                createdAt = examRule.createdAt,
                updatedAt = examRule.updatedAt,
                deletedAt = examRule.deletedAt
            )
        }
    }

}