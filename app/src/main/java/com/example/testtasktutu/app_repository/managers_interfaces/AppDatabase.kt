package com.example.testtasktutu.app_repository.managers_interfaces

import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData

interface AppDatabase {
    suspend fun loadGithubRepositoriesList(login: String): List<GithubRepoBriefInfoData>
    suspend fun updateData(listInsert: List<GithubRepoInfoData>)
    suspend fun loadRepositoryInfo(login: String, name: String): GithubRepoInfoData
    suspend fun updateData(detailsInfoData: GithubRepoInfoData)
}