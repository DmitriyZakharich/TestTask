package com.example.testtasktutu.app_repository.managers_interfaces

import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData

interface GithubReposInfoLoader {
    suspend fun loadGithubReposList(query: String): List<GithubRepoBriefInfoData>
    suspend fun loadGithubRepoInfo(login: String, name: String): GithubRepoInfoData
}