package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ChangeStudentExamsStateApiResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.ChangeStudentExamsStateResponse
import javax.inject.Inject

class SuspendStudentExamsMapper @Inject constructor() : Mapper<BaseResponse<ChangeStudentExamsStateApiResponse>, ChangeStudentExamsStateResponse> {

    override fun mapFromApiResponse(type: BaseResponse<ChangeStudentExamsStateApiResponse>): ChangeStudentExamsStateResponse {
        val apiResponse = type.data
        return ChangeStudentExamsStateResponse(
            message = apiResponse.message
        )
    }

}