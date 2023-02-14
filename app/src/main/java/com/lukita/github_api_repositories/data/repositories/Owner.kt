package com.lukita.github_api_repositories.data.repositories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("login")
    @Expose
    var login: String? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String? = null,
): Serializable