package com.lukita.github_api_repositories.repositorieslist.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.databinding.RepositoryItemBinding

class RepositoriesListViewHolder(
    private val binding: RepositoryItemBinding,
    private val listener: OnRepositoryClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repositoryInfo: RepositoryInfo, position: Int) {
        itemView.apply {
            binding.apply {
                this.textViewRepositoryName.text = repositoryInfo.name
                this.textViewRepositoryDescription.text = repositoryInfo.description
                this.avatarLayout.textViewUserName.text = repositoryInfo.name
                this.avatarLayout.textViewFullName.text = repositoryInfo.full_name
                this.forksAndStarsLayout.textViewForks.text = repositoryInfo.forks
                this.forksAndStarsLayout.textViewStars.text = repositoryInfo.stars
            }

            this.setOnClickListener {
                listener.onRepositoryCardClick(repositoryInfo, position)
            }

            Glide.with(context)
                .load("${repositoryInfo.owner?.avatar_url}")
                .dontAnimate()
                .into(binding.avatarLayout.imageViewAvatar)
        }
    }


    interface OnRepositoryClickListener {
        fun onRepositoryCardClick(repositoryInfo: RepositoryInfo, itemPosition: Int)
    }
}