package com.example.testtasktutu.screens.common_interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelGithubRepoBriefInfo

interface RepositoryManager {
    val listRepos: LiveData<ParcelGithubRepoBriefInfo>
    val detailRepo: LiveData<GithubDetailRepoInfoDomain>
    fun getListData(login: String)
    fun getDetailsData(login: String, name: String)
}
