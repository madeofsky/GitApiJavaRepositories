package com.lukita.github_api_repositories.data.repositories

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoriesListSearchResult(
    val total_count: Int,
    @SerializedName("items")
    val items: List<RepositoryInfo>? = null
): Serializable