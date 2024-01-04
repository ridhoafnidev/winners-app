package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ChangePasswordResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.ChangePassword
import javax.inject.Inject

class ChangePasswordMapper @Inject constructor(): Mapper<BaseResponse<ChangePasswordResponse>, ChangePassword> {
    override fun mapFromApiResponse(type: BaseResponse<ChangePasswordResponse>): ChangePassword {
        val data = type.data
        return ChangePassword(
            data.message
        )
    }

}