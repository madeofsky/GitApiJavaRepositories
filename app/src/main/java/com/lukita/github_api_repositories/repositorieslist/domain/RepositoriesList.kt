package com.lukita.github_api_repositories.repositorieslist.domain

import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.utils.RepositoriesResult

interface RepositoriesList {
    suspend fun getRepositoriesList(): RepositoriesResult<MutableList<RepositoryInfo>>
    suspend fun getPullRequestsInfo(pullsUrl: String): RepositoriesResult<MutableList<PullRequestInfo>>
}