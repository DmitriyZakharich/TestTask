package com.example.testtasktutu.list_screen.domain.mappers

import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain

class RepositoryInfoMapper {
    companion object {
        fun modelListDataToDomain(
                list: List<RepositoryBriefInfoData>): List<RepositoryBriefInfoDomain> {
            val newList = mutableListOf<RepositoryBriefInfoDomain>()

            list.forEach {
                newList.add(RepositoryBriefInfoDomain(
                    login = it.login,
                    name = it.name,
                    description = it.description,
                    language = it.language,
                    stargazers_count = it.stargazers_count,
                    updated_at = it.updated_at))
            }
            return newList
        }
    }
}