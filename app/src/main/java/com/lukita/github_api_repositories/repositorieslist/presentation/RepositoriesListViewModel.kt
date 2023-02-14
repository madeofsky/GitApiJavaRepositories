package com.lukita.github_api_repositories.repositorieslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.repositorieslist.domain.RepositoriesList
import com.lukita.github_api_repositories.utils.RepositoriesListEvent
import com.lukita.github_api_repositories.utils.RepositoriesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesListViewModel @Inject constructor(
    private val repositoriesList: RepositoriesList
) : ViewModel() {

    private val _repositories = MutableStateFlow<RepositoriesListEvent>(RepositoriesListEvent.Empty)
    val repositories: StateFlow<RepositoriesListEvent> = _repositories

    private val _navigation = MutableLiveData<RepositoryInfo>()
    val navigation: LiveData<RepositoryInfo> = _navigation


    fun onViewCreated() = viewModelScope.launch(Dispatchers.IO) {
        _repositories.value = RepositoriesListEvent.Loading

        when (val repositoriesResponse = repositoriesList.getRepositoriesList()) {
            is RepositoriesResult.Success -> {
                val repositories = repositoriesResponse.resultData

                if(repositories == null) {
                    _repositories.value = RepositoriesListEvent.Failure("Error")
                } else {
                    _repositories.value = RepositoriesListEvent.Success(repositories)
                }

            }
            is RepositoriesResult.Error -> {
                _repositories.value = RepositoriesListEvent.Failure(repositoriesResponse.message)
            }
        }
    }

    fun onRepositoryClick(repositoryInfo: RepositoryInfo) {
        _navigation.postValue(repositoryInfo)
    }
}