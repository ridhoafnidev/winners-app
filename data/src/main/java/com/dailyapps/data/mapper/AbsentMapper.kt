package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.AbsentDataItemApiResponse
import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.data.utils.DataMapper.toAbsentDataItems
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.Absent
import javax.inject.Inject

class AbsentMapper @Inject constructor(): Mapper<BaseResponse<List<AbsentDataItemApiResponse>>, Absent> {
    override fun mapFromApiResponse(type: BaseResponse<List<AbsentDataItemApiResponse>>): Absent {
        val data = type.data
        return Absent(data.toAbsentDataItems())
    }

}