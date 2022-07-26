package com.example.testtasktutu.screens.details_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

interface DetailsViewModel {
    val info: LiveData<GithubDetailRepoInfoDomain>
    fun getData(login: String, name: String)
}