package com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces

import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain
import kotlinx.coroutines.CoroutineDispatcher

interface GetListUseCase {
    suspend fun start(query: String, scope: CoroutineDispatcher): List<GithubRepoBriefInfoDomain>
}