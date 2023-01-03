package com.example.testtasktutu.screens.details_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

interface GetDataUseCase {
//    val info: LiveData<GithubDetailRepoInfoDomain>
suspend fun start(login: String, name: String): GithubDetailRepoInfoDomain
}