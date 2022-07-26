package com.example.testtasktutu.screens.details_screen.domain.model

data class GithubDetailRepoInfoDomain(
        var login: String? = "",
        var name: String? = "",  //repository name
        var description: String? = "",
        var language: String? = "",
        var stargazers_count: Int? = 0,
        var updated_at: String? = ""
)
