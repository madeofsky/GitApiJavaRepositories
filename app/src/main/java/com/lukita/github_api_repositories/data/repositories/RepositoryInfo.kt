package com.lukita.github_api_repositories.data.repositories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryInfo(
    @SerializedName("id")
    @Expose
    var id: Long? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("full_name")
    @Expose
    var full_name: String? = null,
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("forks_count")
    @Expose
    var forks: String? = null,
    @SerializedName("stargazers_count")
    @Expose
    var stars: String? = null,
    @SerializedName("pulls_url")
    @Expose
    var pulls_url: String? = null,
): Serializable