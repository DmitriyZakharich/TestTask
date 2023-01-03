package com.example.testtasktutu.screens.list_screen.domain

import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase

class GetAdapterUseCaseImpl(private val repositoryManager: RepositoryManager) : GetAdapterUseCase {

     override suspend fun start(query: String): CustomRecyclerAdapter {
        return CustomRecyclerAdapter(repositoryManager.getListData(query))
    }
}