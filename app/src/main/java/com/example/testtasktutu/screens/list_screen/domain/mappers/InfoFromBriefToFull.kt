package com.example.testtasktutu.screens.list_screen.domain.mappers

import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData

class InfoFromBriefToFull {
    companion object {
        fun convert(list: List<GithubRepoBriefInfoData>?): List<GithubRepoInfoData> {
            val listFullInfo = mutableListOf<GithubRepoInfoData>()
             list?.forEach{
                 listFullInfo.add(GithubRepoInfoData(it.login, it.name, it.description))
            }
            return listFullInfo
        }
    }
}