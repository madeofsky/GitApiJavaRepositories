package com.lukita.github_api_repositories.repositorieslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.databinding.RepositoryItemBinding
import com.lukita.github_api_repositories.repositorieslist.ui.viewholder.RepositoriesListViewHolder

class RepositoriesListAdapter(
    private val repositories: List<RepositoryInfo>,
    private val listener: RepositoriesListViewHolder.OnRepositoryClickListener
) : RecyclerView.Adapter<RepositoriesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListViewHolder {
        val binding = RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesListViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RepositoriesListViewHolder, position: Int) {
        val repositories = repositories[position]
        holder.bind(repositories, position)
    }

    override fun getItemCount(): Int = repositories.size
}