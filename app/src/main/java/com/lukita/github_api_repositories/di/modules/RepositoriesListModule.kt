package com.lukita.github_api_repositories.di.modules

import com.lukita.github_api_repositories.data.RepositoriesListServiceInstance
import com.lukita.github_api_repositories.repositorieslist.domain.RepositoriesList
import com.lukita.github_api_repositories.repositorieslist.domain.RepositoriesListImpl
import com.lukita.github_api_repositories.utils.GitConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface RepositoriesListModule {

    @Binds
    fun provideGitRepositories(gitRepositories: RepositoriesListImpl): RepositoriesList


    companion object {
        @Singleton
        @Provides
        fun provideGitRepositoriesServiceInstanceInterface(): RepositoriesListServiceInstance =
            Retrofit.Builder()
                .baseUrl(GitConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RepositoriesListServiceInstance::class.java)

        @Singleton
        @Provides
        fun provideGitRepositoriesDefaultRepository(apiInstance: RepositoriesListServiceInstance): RepositoriesListImpl =
            RepositoriesListImpl(apiInstance)
    }
}