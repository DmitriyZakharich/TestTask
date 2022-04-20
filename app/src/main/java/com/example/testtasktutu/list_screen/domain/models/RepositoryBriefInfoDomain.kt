package com.example.testtasktutu.list_screen.domain.models

data class RepositoryBriefInfoDomain(
        val login: String?,
        val name: String?,  //repository name
        val description: String?,
        val language: String?,
        val stargazers_count: Int?,
        val updated_at: String?)
