package com.lukita.github_api_repositories.pullrequestinfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukita.github_api_repositories.data.pullrequests.PullRequestInfo
import com.lukita.github_api_repositories.data.repositories.RepositoryInfo
import com.lukita.github_api_repositories.repositorieslist.domain.RepositoriesList
import com.lukita.github_api_repositories.utils.PullRequestInfoEvent
import com.lukita.github_api_repositories.utils.RepositoriesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PullRequestsInfoDataClass(
    val openPulls: String,
    val closedPulls: String
)

class PullRequestInfoViewModel @Inject constructor(
    private val repositoriesList: RepositoriesList
) : ViewModel() {

    private val _repositoryInfo = MutableStateFlow<PullRequestInfoEvent>(PullRequestInfoEvent.Empty)
    val repositoryInfo: StateFlow<PullRequestInfoEvent> = _repositoryInfo

    private val _pullRequestsInfo = MutableLiveData<PullRequestsInfoDataClass>()
    val pullRequestsInfo: LiveData<PullRequestsInfoDataClass> = _pullRequestsInfo

    fun onViewCreated(repositoryInfo: RepositoryInfo) = viewModelScope.launch(Dispatchers.IO) {
        _repositoryInfo.value = PullRequestInfoEvent.Loading

        when (val pullRequestsResponse = repositoriesList.getPullRequestsInfo(repositoryInfo.pulls_url?: "")) {
            is RepositoriesResult.Success -> {
                val pullRequests = pullRequestsResponse.resultData

                if(pullRequests == null) {
                    _repositoryInfo.value = PullRequestInfoEvent.Failure("Error")
                } else {
                    _repositoryInfo.value = PullRequestInfoEvent.Success(pullRequests)
                    _pullRequestsInfo.postValue(PullRequestsInfoDataClass(
                        openPulls = "16",
                        closedPulls = "800"
                    ))
                }

            }
            is RepositoriesResult.Error -> {
                _repositoryInfo.value = PullRequestInfoEvent.Failure(pullRequestsResponse.message)
            }
        }
    }

    fun onPullRequestClick(pullRequestInfo: PullRequestInfo) {
    }
}
