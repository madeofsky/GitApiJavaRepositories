package com.lukita.github_api_repositories.data

import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.data.repositories.RepositoriesListSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RepositoriesListServiceInstance {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<RepositoriesListSearchResult>

    @GET
    suspend fun fetchRepositoryInfo(
        @Url pullsUrl: String,
    ): Response<List<PullRequestInfo>>
}