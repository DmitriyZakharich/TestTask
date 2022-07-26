package com.example.testtasktutu.screens.details_screen.domain.mappers

import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain

class RepositoriesInfoMapper {
    companion object {
        fun modelRepositoriesInfoToDomain(infoData: GithubRepoInfoData): GithubDetailRepoInfoDomain {

            return GithubDetailRepoInfoDomain(login = infoData.login, name = infoData.name,
                description = infoData.description, language = infoData.language,
                stargazers_count = infoData.stargazers_count, updated_at = infoData.updated_at)
        }
    }
}