package com.example.testtasktutu.screens.list_screen.presentation.viewstate

import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain

sealed class ListState {

    object Idle : ListState()
    object Loading : ListState()
    data class Repos(val repos: List<GithubRepoBriefInfoDomain>) : ListState()
    data class Error(val error: String?) : ListState()

}
