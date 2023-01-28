package com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces

import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain

interface GetListUseCase {
    suspend fun start(query: String): List<GithubRepoBriefInfoDomain>
}