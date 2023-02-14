package com.lukita.github_api_repositories

import android.app.Application
import com.lukita.github_api_repositories.di.components.ApplicationComponent
import com.lukita.github_api_repositories.di.components.DaggerApplicationComponent

open class GitApplication: Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}