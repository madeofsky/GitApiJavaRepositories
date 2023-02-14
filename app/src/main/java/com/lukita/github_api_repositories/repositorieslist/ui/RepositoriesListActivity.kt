package com.lukita.github_api_repositories.repositorieslist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukita.github_api_repositories.GitApplication
import com.lukita.github_api_repositories.databinding.ActivityRepositoriesListBinding
import com.lukita.github_api_repositories.di.components.RepositoriesListActivityComponent
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.repositorieslist.presentation.RepositoriesListViewModel
import com.lukita.github_api_repositories.repositorieslist.ui.adapter.RepositoriesListAdapter
import com.lukita.github_api_repositories.repositorieslist.ui.viewholder.RepositoriesListViewHolder
import com.lukita.github_api_repositories.pullrequestinfo.ui.PullRequestInfoActivity
import com.lukita.github_api_repositories.utils.RepositoriesListEvent
import javax.inject.Inject

class RepositoriesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoriesListBinding

    @Inject
    lateinit var viewModel: RepositoriesListViewModel

    val activityComponent: RepositoriesListActivityComponent by lazy {
        (application as GitApplication).appComponent.repositoriesActivityComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityRepositoriesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.onViewCreated()

        setSupportActionBar(binding.repositoriesToolbar)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.repositories.collect { event ->
                when (event) {
                    is RepositoriesListEvent.Loading -> {
                        binding.loadingLottieAnimation.apply {
                            this.visibility = View.VISIBLE
                            this.playAnimation()
                        }
                    }
                    is RepositoriesListEvent.Failure -> {
                        Toast.makeText(baseContext, event.message, Toast.LENGTH_SHORT).show()
                        Log.d("RepositoriesListActivit", event.message?: "")
                    }
                    is RepositoriesListEvent.Success -> initAdapter(event.repositories)
                    else -> Unit
                }
            }
        }

        viewModel.navigation.observe(this) { repository ->
            val intent = Intent(this, PullRequestInfoActivity::class.java)
            intent.putExtra("repository", repository)
            startActivity(intent)
        }
    }

    private fun initAdapter(repositories: List<RepositoryInfo>) {
        val repositoriesListAdapter = RepositoriesListAdapter(repositories, object : RepositoriesListViewHolder.OnRepositoryClickListener {
            override fun onRepositoryCardClick(repositoryInfo: RepositoryInfo, itemPosition: Int) {
                viewModel.onRepositoryClick(repositoryInfo)
            }
        })

        val linearLayoutManager = LinearLayoutManager(baseContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        binding.loadingLottieAnimation.apply {
            this.cancelAnimation()
            this.visibility = View.GONE
        }

        binding.repositoriesRecyclerView.apply {
            this.visibility = View.VISIBLE
            this.layoutManager = linearLayoutManager
            this.adapter = repositoriesListAdapter
        }
    }
}