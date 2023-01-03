package com.example.testtasktutu.app_repository

import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.details_screen.domain.mappers.RepositoriesInfoMapper
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.list_screen.domain.mappers.GithubRepoInfoMapper
import com.example.testtasktutu.screens.list_screen.domain.mappers.InfoFromBriefToFull
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain
import com.example.testtasktutu.utils.checkForInternet

class RepositoryManagerImpl(private val githubReposInfoLoader: GithubReposInfoLoader,
        private val appDatabase: AppDatabase) : RepositoryManager {

    override suspend fun getListData(login: String): List<GithubRepoBriefInfoDomain> {
        return if (checkForInternet()) {

            val list = githubReposInfoLoader.loadGithubReposList(login)
            if (list.isNotEmpty())
                appDatabase.updateData(InfoFromBriefToFull.convert(list))
            GithubRepoInfoMapper.modelListDataToDomain(list)

        } else {
            val list = appDatabase.loadGithubRepositoriesList(login)
            GithubRepoInfoMapper.modelListDataToDomain(list)
        }
    }

    override suspend fun getDetailsData(login: String, name: String): GithubDetailRepoInfoDomain {
        return if (checkForInternet()) {

            val info = githubReposInfoLoader.loadGithubRepoInfo(login = login, name = name)
            appDatabase.updateData(detailsInfoData = info)
            RepositoriesInfoMapper.modelRepositoriesInfoToDomain(info)

        } else {
            val info =  appDatabase.loadRepositoryInfo(login = login, name = name)
            RepositoriesInfoMapper.modelRepositoriesInfoToDomain(info)
        }
    }
}