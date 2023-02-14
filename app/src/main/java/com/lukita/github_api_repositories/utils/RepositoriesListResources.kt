package com.lukita.github_api_repositories.utils

import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo

sealed class RepositoriesResult<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : RepositoriesResult<T>(resultData, null)
    class Error<T>(message: String) : RepositoriesResult<T>(null, message)
}

sealed class RepositoriesListEvent {
    class Success(val repositories: List<RepositoryInfo>) : RepositoriesListEvent()
    class Failure(val message: String?) : RepositoriesListEvent()
    object Loading : RepositoriesListEvent()
    object Empty : RepositoriesListEvent()
}

sealed class PullRequestInfoEvent {
    class Success(val pullRequestInfo: List<PullRequestInfo>) : PullRequestInfoEvent()
    class Failure(val message: String?) : PullRequestInfoEvent()
    object Loading : PullRequestInfoEvent()
    object Empty : PullRequestInfoEvent()
}