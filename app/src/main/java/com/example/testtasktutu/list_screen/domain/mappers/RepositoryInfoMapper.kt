package com.example.testtasktutu.list_screen.domain.mappers

import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

class RepositoryInfoMapper {


    companion object{
        fun modelListDataToDomain(list: List<RepositoryInfoData>): List<RepositoryInfoDomain> {
            val newList = mutableListOf<RepositoryInfoDomain>()

            list.forEach{
                newList.add(RepositoryInfoDomain(
                    it.repositoryName,
                    it.description,
                    it.language,
                    it.stargazers_count,
                    it.updated_at))
            }
            return newList
        }

        fun modelListDomainToData(userName: String, repositoryInfoData: List<RepositoryInfoDomain>): List<RepositoryInfoData> {
            val newList = mutableListOf<RepositoryInfoData>()

            repositoryInfoData.forEach{
                newList.add(RepositoryInfoData(
                    userName,
                    it.repositoryName ?: "",
                    it.description ?: "",
                    it.language ?: "",
                    it.stargazers_count ?: 0,
                    it.updated_at ?: ""))
            }
            return newList
        }
    }
}