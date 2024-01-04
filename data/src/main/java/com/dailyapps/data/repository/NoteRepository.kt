package com.dailyapps.data.repository

import com.dailyapps.data.remote.datasource.NoteRemoteDataSource
import com.dailyapps.domain.repository.INoteRepository
import com.dailyapps.domain.usecase.NoteUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
        private val remoteDataSource: NoteRemoteDataSource
) : INoteRepository {
    override suspend fun getNoteBySiswaId(params: NoteUseCase.Params): Flow<Resource<List<Note>>> =
        remoteDataSource.getPelanggaranBySiswaId(params)
}