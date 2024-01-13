package com.example.testtasktutu.screens.details_screen.presentation.viewstate

import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

sealed class DetailsState {
    object Idle : DetailsState()
    object Loading : DetailsState()
    data class Details(val data: GithubDetailRepoInfoDomain) : DetailsState()
    data class Error(val error: String?) : DetailsState()
}
