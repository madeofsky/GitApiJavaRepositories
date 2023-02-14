package com.lukita.github_api_repositories.data.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PullRequestInfo(
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("user")
    @Expose
    var user: PullRequestUserInfo? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("body")
    @Expose
    var body: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("open_issues_count")
    @Expose
    var open_issues_count: String? = null
): Serializable