package com.dailyapps.data.remote.datasource

import com.dailyapps.data.mapper.NoteMapper
import com.dailyapps.data.remote.service.NoteService
import com.dailyapps.data.utils.apiCall
import com.dailyapps.data.utils.mapFromApiResponse
import com.dailyapps.domain.usecase.NoteUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRemoteDataSource @Inject constructor(
    private val noteService: NoteService,
    private val noteMapper: NoteMapper
){
    suspend fun getPelanggaranBySiswaId(params: NoteUseCase.Params) : Flow<Resource<List<Note>>> {
        return mapFromApiResponse(
            result = apiCall {
                noteService.getNoteById(params.token, params.siswaId, params.tahunAjaranSemesterId)
            }, noteMapper
        )
    }
}