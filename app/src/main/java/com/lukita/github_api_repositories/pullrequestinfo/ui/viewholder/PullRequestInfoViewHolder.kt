package com.lukita.github_api_repositories.pullrequestinfo.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.databinding.PullRequestItemBinding

class PullRequestInfoViewHolder(
    private val binding: PullRequestItemBinding,
    private val listener: OnPullRequestClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pullRequestInfo: PullRequestInfo, position: Int) {
        itemView.apply {
            binding.apply {
                this.textViewPullRequestTitle.text = pullRequestInfo.title
                this.textViewPullRequestDescription.text = pullRequestInfo.body
                this.avatarLayout.textViewUserName.text = pullRequestInfo.user?.login
                this.avatarLayout.textViewFullName.text = pullRequestInfo.user?.login
                this.pullDateLayout.textViewCreatedAtDate.text = pullRequestInfo.createdAt
                this.pullDateLayout.textViewUpdatedAtDate.text = pullRequestInfo.updatedAt
            }

            this.setOnClickListener {
                listener.onPullRequestClick(pullRequestInfo, position)
            }

            Glide.with(context)
                .load("${pullRequestInfo.user?.avatar_url}")
                .dontAnimate()
                .into(binding.avatarLayout.imageViewAvatar)
        }
    }


    interface OnPullRequestClickListener {
        fun onPullRequestClick(pullRequestInfo: PullRequestInfo, itemPosition: Int)
    }
}