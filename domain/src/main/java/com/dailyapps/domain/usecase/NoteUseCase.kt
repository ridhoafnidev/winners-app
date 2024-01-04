package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.INoteRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    private val repository: INoteRepository
): ApiUseCaseParams<NoteUseCase.Params, List<Note>> {
    override suspend fun execute(params: Params): Flow<Resource<List<Note>>> =
        repository.getNoteBySiswaId(params)


    data class Params(val siswaId: Int, val token: String, val tahunAjaranSemesterId: Int)

}