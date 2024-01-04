package com.dailyapps.domain.usecase

import com.dailyapps.domain.repository.IGithubRepository
import com.dailyapps.domain.utils.ApiUseCaseParams
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoUseCase @Inject constructor(
    private val repository: IGithubRepository
):ApiUseCaseParams<RepoUseCase.Params, List<Repo>>{

    override suspend fun execute(params: Params): Flow<Resource<List<Repo>>> {
        return repository.fetchRepo(params)
    }

    data class Params(val userName:String)
}