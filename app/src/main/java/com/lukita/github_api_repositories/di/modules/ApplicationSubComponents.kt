package com.lukita.github_api_repositories.di.modules

import com.lukita.github_api_repositories.di.components.RepositoriesListActivityComponent
import com.lukita.github_api_repositories.di.components.PullRequestInfoActivityComponent
import dagger.Module

@Module(subcomponents = [RepositoriesListActivityComponent::class, PullRequestInfoActivityComponent::class])
interface ApplicationSubComponents