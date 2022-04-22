package com.example.testtasktutu.details_screen.domain.mappers

import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.details_screen.domain.model.RepositoriesInfoDomain

class RepositoriesInfoMapper {
    companion object {
        fun modelRepositoriesInfoToDomain(infoData: RepositoriesInfoData): RepositoriesInfoDomain {

            return RepositoriesInfoDomain(login = infoData.login, name = infoData.name,
                description = infoData.description, language = infoData.language,
                stargazers_count = infoData.stargazers_count, updated_at = infoData.updated_at)
        }
    }
}