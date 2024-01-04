package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.NoteUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    suspend fun getNoteBySiswaId(params: NoteUseCase.Params): Flow<Resource<List<Note>>>
}