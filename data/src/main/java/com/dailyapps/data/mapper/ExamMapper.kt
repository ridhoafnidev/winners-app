package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ExamApiResponse
import com.dailyapps.data.utils.DataMapper.toDomainSchoolYear
import com.dailyapps.data.utils.DataMapper.toGuruDomain
import com.dailyapps.data.utils.DataMapper.toJenisNilaiDomain
import com.dailyapps.data.utils.DataMapper.toMapelDomain
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.Exam
import javax.inject.Inject

class ExamMapper @Inject constructor(): Mapper<BaseResponse<List<ExamApiResponse>>, List<Exam>> {
    override fun mapFromApiResponse(type: BaseResponse<List<ExamApiResponse>>): List<Exam> {
        return type.data.map { data ->
            Exam(
                guru = data.guru?.toGuruDomain(),
                ujianStart = data.ujianStart,
                isActive = data.isActive,
                jenisUjian = data.jenisUjian,
                matpelId = data.matpelId,
                guruId = data.guruId,
                ujianEnd = data.ujianEnd,
                token = data.token,
                expiredDuration = data.expiredDuration,
                tahunAjaranSemester = data.tahunAjaranSemester?.toDomainSchoolYear(),
                tahunAjaranSemesterId = data.tahunAjaranSemesterId,
                matpel = data.matpel?.toMapelDomain(),
                id = data.id,
                isFinish = data.isFinish,
                isStart = data.isStart,
                jenisNilai = data.jenisNilai?.toJenisNilaiDomain(),
                status = data.status
            )
        }

    }

}