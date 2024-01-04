package com.dailyapps.domain.repository

import com.dailyapps.domain.usecase.RepoUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Repo
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    suspend fun fetchRepo(params: RepoUseCase.Params):Flow<Resource<List<Repo>>>
}