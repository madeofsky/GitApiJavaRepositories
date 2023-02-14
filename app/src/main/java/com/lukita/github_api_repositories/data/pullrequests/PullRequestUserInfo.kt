package com.lukita.github_api_repositories.data.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PullRequestUserInfo(
    @SerializedName("login")
    @Expose
    var login: String? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String? = null
): Serializable