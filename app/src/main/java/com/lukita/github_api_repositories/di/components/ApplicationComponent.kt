package com.lukita.github_api_repositories.di.components

import android.content.Context
import com.lukita.github_api_repositories.GitApplication
import com.lukita.github_api_repositories.di.modules.ApplicationSubComponents
import com.lukita.github_api_repositories.di.modules.RepositoriesListModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationSubComponents::class, RepositoriesListModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(application: GitApplication)
    fun repositoriesActivityComponent(): RepositoriesListActivityComponent.Factory
    fun pullRequestInfoActivityComponent(): PullRequestInfoActivityComponent.Factory
}