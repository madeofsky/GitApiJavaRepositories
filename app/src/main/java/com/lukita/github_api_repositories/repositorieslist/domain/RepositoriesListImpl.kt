package com.lukita.github_api_repositories.repositorieslist.domain

import com.lukita.github_api_repositories.data.RepositoriesListServiceInstance
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.utils.GitConstants
import com.lukita.github_api_repositories.utils.RepositoriesResult
import javax.inject.Inject

class RepositoriesListImpl @Inject constructor(
    private val apiInstance: RepositoriesListServiceInstance
) : RepositoriesList {

    override suspend fun getRepositoriesList(): RepositoriesResult<MutableList<RepositoryInfo>> {
        var page = 1
        val allJavaRepositories = mutableListOf<RepositoryInfo>()
        var isLastPage = false

        try {
            while (!isLastPage && page < 3) {
                val response = apiInstance.searchRepositories(GitConstants.QUERY, GitConstants.SORT, page)

                if (response.isSuccessful) {
                    val result = response.body()
                    result?.items?.let { allJavaRepositories.addAll(it) }
                    isLastPage = result?.items?.isEmpty() ?: true
                    page++
                } else {
                    return RepositoriesResult.Error(response.message())
                }
            }
            return RepositoriesResult.Success(allJavaRepositories)
        } catch (e: Exception) {
            return RepositoriesResult.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getPullRequestsInfo(pullsUrl: String): RepositoriesResult<MutableList<PullRequestInfo>> {
        val pullRequestInfo = mutableListOf<PullRequestInfo>()

        try {
            val response = apiInstance.fetchRepositoryInfo(pullsUrl.replace("{/number}", ""))
            val result = response.body()

            if(response.isSuccessful && result != null) {
                result.let { pullRequestInfo.addAll(it)  }
            } else {
                return RepositoriesResult.Error(response.message())
            }
            return RepositoriesResult.Success(pullRequestInfo)
        } catch (e: Exception) {
            return RepositoriesResult.Error(e.message ?: "An error occurred")
        }
    }
}