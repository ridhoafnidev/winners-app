package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.NoteApiResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.Note
import javax.inject.Inject

class NoteMapper @Inject constructor(): Mapper<BaseResponse<List<NoteApiResponse>>, List<Note>> {
    override fun mapFromApiResponse(type: BaseResponse<List<NoteApiResponse>>): List<Note> {
        return type.data.map { data ->
            Note(
                pelanggaran = data.pelanggaran,
                tanggalPelanggaran = data.tanggalPelanggaran,
                updatedAt = data.updatedAt,
                tahunAjaranSemesterId = data.tahunAjaranSemesterId,
                createdAt = data.createdAt,
                id = data.id,
                deletedAt = data.deletedAt,
                siswaId = data.siswaId
            )
        }

    }

}