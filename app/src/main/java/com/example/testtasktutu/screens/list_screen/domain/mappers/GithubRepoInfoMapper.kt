package com.example.testtasktutu.screens.list_screen.domain.mappers

import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain

class GithubRepoInfoMapper {
    companion object {
        fun modelListDataToDomain(
                list: List<GithubRepoBriefInfoData>): List<GithubRepoBriefInfoDomain> {
            val newList = mutableListOf<GithubRepoBriefInfoDomain>()

            list.forEach {
                newList.add(GithubRepoBriefInfoDomain(login = it.login, name = it.name,
                    description = it.description))
            }
            return newList
        }
    }
}