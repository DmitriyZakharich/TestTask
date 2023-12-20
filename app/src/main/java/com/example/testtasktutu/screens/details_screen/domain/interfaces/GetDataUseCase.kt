package com.example.testtasktutu.screens.details_screen.domain.interfaces

import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

interface GetDataUseCase {
    suspend fun start(login: String, name: String): GithubDetailRepoInfoDomain
}