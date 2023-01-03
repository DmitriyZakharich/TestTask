package com.example.testtasktutu.screens.common_interfaces

import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain

interface RepositoryManager {
    suspend fun getListData(login: String): List<GithubRepoBriefInfoDomain>
    suspend fun getDetailsData(login: String, name: String): GithubDetailRepoInfoDomain
}
