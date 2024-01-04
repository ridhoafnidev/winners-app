package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.LoginApiResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.User
import javax.inject.Inject

class AuthMapper @Inject constructor(): Mapper<BaseResponse<LoginApiResponse>, User> {
    override fun mapFromApiResponse(type: BaseResponse<LoginApiResponse>): User {
        val data = type.data.user
        val token = type.data
        return User(
            data.profilePhotoUrl,
            data.roles,
            data.name,
            data.id,
            data.email,
            data.username,
            token.accessToken,
            token.tokenType
        )
    }

}