package com.example.testtasktutu.app_repository.managers_interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_repository.models.ParcelDetailsInfo
import com.example.testtasktutu.app_repository.models.ParcelGitHubReposInfo

interface GithubReposInfoLoader {
    val parcelDetailsInfo: LiveData<ParcelDetailsInfo>
    val parcelRepositoryInfo: LiveData<ParcelGitHubReposInfo>
    fun loadGithubReposList(query: String)
    fun loadGithubRepoInfo(login: String, name: String)
}