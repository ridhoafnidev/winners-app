package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.ScoreApiResponse
import com.dailyapps.data.utils.DataMapper.toGuruDomain
import com.dailyapps.data.utils.DataMapper.toJenisNilaiDomain
import com.dailyapps.data.utils.DataMapper.toMapelDomain
import com.dailyapps.data.utils.DataMapper.toNamaNilaiDomain
import com.dailyapps.data.utils.DataMapper.toSiswaDomain
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.Score
import javax.inject.Inject

class ScoreMapper @Inject constructor(): Mapper<BaseResponse<List<ScoreApiResponse>>, List<Score>> {
    override fun mapFromApiResponse(type: BaseResponse<List<ScoreApiResponse>>): List<Score> {
        return type.data.map { data ->
            Score(
                jenisNilai = data.jenisNilai?.toJenisNilaiDomain(),
                siswa = data.siswa?.toSiswaDomain(),
                guru = data.guru?.toGuruDomain(),
                matpel = data.matpel?.toMapelDomain(),
                nilai = data.nilai,
                createdAt = data.createdAt,
                matpelId = data.matpelId,
                jenisNilaiId = data.jenisNilaiId,
                guruId = data.guruId,
                namaNilai = data.namaNilai?.toNamaNilaiDomain(),
                updatedAt = data.updatedAt,
                tahunAjaranSemesterId = data.tahunAjaranSemesterId,
                id = data.id,
                deletedAt = data.deletedAt,
                siswaId = data.siswaId
            )
        }

    }

}