package com.lukita.github_api_repositories.di.components

import com.lukita.github_api_repositories.pullrequestinfo.ui.PullRequestInfoActivity
import dagger.Subcomponent

@Subcomponent
interface PullRequestInfoActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PullRequestInfoActivityComponent
    }

    fun inject(activity: PullRequestInfoActivity)
}