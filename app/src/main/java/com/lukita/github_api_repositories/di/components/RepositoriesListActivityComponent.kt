package com.lukita.github_api_repositories.di.components

import com.lukita.github_api_repositories.repositorieslist.ui.RepositoriesListActivity
import dagger.Subcomponent

@Subcomponent
interface RepositoriesListActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoriesListActivityComponent
    }

    fun inject(activity: RepositoriesListActivity)
}