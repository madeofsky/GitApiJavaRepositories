package com.lukita.github_api_repositories.pullrequestinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.databinding.PullRequestItemBinding
import com.lukita.github_api_repositories.pullrequestinfo.ui.viewholder.PullRequestInfoViewHolder

class PullRequestInfoAdapter(
    private val pullRequests: List<PullRequestInfo>,
    private val listener: PullRequestInfoViewHolder.OnPullRequestClickListener
) : RecyclerView.Adapter<PullRequestInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestInfoViewHolder {
        val binding = PullRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestInfoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PullRequestInfoViewHolder, position: Int) {
        val repositories = pullRequests[position]
        holder.bind(repositories, position)
    }

    override fun getItemCount(): Int = pullRequests.size
}