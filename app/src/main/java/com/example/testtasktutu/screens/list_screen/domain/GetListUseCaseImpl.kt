package com.example.testtasktutu.screens.list_screen.domain

import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase

class GetListUseCaseImpl(private val repositoryManager: RepositoryManager) : GetListUseCase {

     override suspend fun start(query: String): List<GithubRepoBriefInfoDomain> {
        return repositoryManager.getListData(query)
    }
}