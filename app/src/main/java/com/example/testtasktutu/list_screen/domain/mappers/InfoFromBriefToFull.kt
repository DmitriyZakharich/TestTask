package com.example.testtasktutu.list_screen.domain.mappers

import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData

class InfoFromBriefToFull {
    companion object {
        fun convert(list: List<RepositoryBriefInfoData>?): List<RepositoriesInfoData> {
            val listFullInfo = mutableListOf<RepositoriesInfoData>()
             list?.forEach{
                 listFullInfo.add(RepositoriesInfoData(it.login, it.name, it.description))
            }
            return listFullInfo
        }
    }

}