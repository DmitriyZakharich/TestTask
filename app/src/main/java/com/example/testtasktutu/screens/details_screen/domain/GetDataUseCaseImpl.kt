package com.example.testtasktutu.screens.details_screen.domain

import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

class GetDataUseCaseImpl(private val repositoryManager: RepositoryManager) : GetDataUseCase {

    override suspend fun start(login: String, name: String): GithubDetailRepoInfoDomain {
        return repositoryManager.getDetailsData(login = login, name = name)
    }
}