package com.example.testtasktutu.app_repository.managers_interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData

interface AppDatabase {
    val repositoriesList: LiveData<List<GithubRepoBriefInfoData>?>
    val repositoryInfo: LiveData<GithubRepoInfoData?>
    fun loadGithubRepositoriesList(login: String)
    fun updateData(login: String, listInsert: List<GithubRepoInfoData>)
    fun loadRepositoryInfo(login: String, name: String)
    fun updateData(detailsInfoData: GithubRepoInfoData)
}