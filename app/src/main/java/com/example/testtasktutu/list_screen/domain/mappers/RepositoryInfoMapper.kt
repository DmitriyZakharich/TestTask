package com.example.testtasktutu.list_screen.domain.mappers

import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain

class RepositoryInfoMapper {
    companion object {
        fun modelListDataToDomain(
                list: List<RepositoryBriefInfoData>): List<RepositoryBriefInfoDomain> {
            val newList = mutableListOf<RepositoryBriefInfoDomain>()

            list.forEach {
                newList.add(RepositoryBriefInfoDomain(login = it.login, name = it.name,
                    description = it.description))
            }
            return newList
        }
    }
}