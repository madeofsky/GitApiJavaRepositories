package com.lukita.github_api_repositories.pullrequestinfo.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.L
import com.lukita.github_api_repositories.GitApplication
import com.lukita.github_api_repositories.R
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.databinding.ActivityPullRequestInfoBinding
import com.lukita.github_api_repositories.di.components.PullRequestInfoActivityComponent
import com.lukita.github_api_repositories.pullrequestinfo.presentation.PullRequestInfoViewModel
import com.lukita.github_api_repositories.pullrequestinfo.ui.adapter.PullRequestInfoAdapter
import com.lukita.github_api_repositories.pullrequestinfo.ui.viewholder.PullRequestInfoViewHolder
import com.lukita.github_api_repositories.utils.PullRequestInfoEvent
import com.lukita.github_api_repositories.utils.RepositoriesListEvent
import javax.inject.Inject

class PullRequestInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestInfoBinding

    @Inject
    lateinit var viewModel: PullRequestInfoViewModel

    val activityComponent: PullRequestInfoActivityComponent by lazy {
        (application as GitApplication).appComponent.pullRequestInfoActivityComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repositoryInfo = intent.getSerializableExtra("repository") as? RepositoryInfo
        if (repositoryInfo != null) {
            viewModel.onViewCreated(repositoryInfo)
            setToolbar(repositoryInfo.name)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.repositoryInfo.collect { event ->
                when (event) {
                    is PullRequestInfoEvent.Loading -> {
                        binding.loadingLottieAnimation.apply {
                            this.visibility = View.VISIBLE
                            this.playAnimation()
                        }
                    }
                    is PullRequestInfoEvent.Failure -> {
                        Toast.makeText(baseContext, event.message?: "Error", Toast.LENGTH_SHORT).show()
                    }
                    is PullRequestInfoEvent.Success -> {
                        if (event.pullRequestInfo.isEmpty()) {
                            showNothingToSeeLottieAnimation()
                        } else {
                            initAdapter(event.pullRequestInfo)
                        }
                    }
                    else -> Unit
                }
            }
        }

        viewModel.pullRequestsInfo.observe(this) {
            binding.openClosedPullsLayout.visibility = View.VISIBLE
            binding.textViewOpenedPulls.text = getString(R.string.pull_request_open_issues, it.openPulls)
            binding.textViewClosedPulls.text = getString(R.string.pull_request_closed_issues, it.closedPulls)
        }
    }

    private fun initAdapter(pullRequests: List<PullRequestInfo>) {
        val pullRequestInfoAdapter = PullRequestInfoAdapter(pullRequests, object : PullRequestInfoViewHolder.OnPullRequestClickListener {
            override fun onPullRequestClick(pullRequestInfo: PullRequestInfo, itemPosition: Int) {
                viewModel.onPullRequestClick(pullRequestInfo)
            }
        })

        val linearLayoutManager = LinearLayoutManager(baseContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        binding.loadingLottieAnimation.apply {
            this.cancelAnimation()
            this.visibility = View.GONE
        }

        binding.pullRequestsRecyclerView.apply {
            this.visibility = View.VISIBLE
            this.layoutManager = linearLayoutManager
            this.adapter = pullRequestInfoAdapter
        }
    }

    private fun setToolbar(toolbarTitle: String?) {
        binding.pullRequestsToolbar.apply {
            this.title = toolbarTitle?: ""
            this.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    private fun showNothingToSeeLottieAnimation() {
        binding.loadingLottieAnimation.apply {
            this.cancelAnimation()
            this.visibility = View.GONE
        }
        binding.nothingToSeeHereLottieAnimation.apply {
            this.visibility = View.VISIBLE
            this.playAnimation()
        }
    }
}