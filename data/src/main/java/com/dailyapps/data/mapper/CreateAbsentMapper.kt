package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.CreateAbsentResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.CreateAbsent
import javax.inject.Inject

class CreateAbsentMapper @Inject constructor() :
    Mapper<BaseResponse<CreateAbsentResponse>, CreateAbsent> {

    override fun mapFromApiResponse(type: BaseResponse<CreateAbsentResponse>): CreateAbsent {
        val data = type.data
        return CreateAbsent(
            jenisAbsensiId = data.jenisAbsensiId,
            tanggalAbsensi = data.tanggalAbsensi,
            foto = data.foto,
            jamMasuk = data.jamMasuk,
            latitude = data.latitude,
            tahunAjaranSemesterId = data.tahunAjaranSemesterId,
            longitude = data.longitude
        )
    }

}