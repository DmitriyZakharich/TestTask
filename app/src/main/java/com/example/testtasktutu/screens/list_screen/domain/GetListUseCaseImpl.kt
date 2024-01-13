package com.example.testtasktutu.screens.list_screen.domain

import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetListUseCaseImpl(private val repositoryManager: RepositoryManager) : GetListUseCase {

     override suspend fun start(query: String, scope: CoroutineDispatcher): List<GithubRepoBriefInfoDomain> =
         withContext(scope){
             return@withContext repositoryManager.getListData(query)
         }
}