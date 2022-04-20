package com.example.testtasktutu.details_screen.domain.mappers

import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import com.example.testtasktutu.details_screen.domain.model.DetailsInfoDomain

class DetailsInfoMapper {
    companion object {
        fun modelDetailsInfoToDomain(infoData: DetailsInfoData): DetailsInfoDomain {
            return DetailsInfoDomain(login = infoData.login, name = infoData.name,
                description = infoData.description, language = infoData.language,
                stargazers_count = infoData.stargazers_count, updated_at = infoData.updated_at)
        }
    }
}