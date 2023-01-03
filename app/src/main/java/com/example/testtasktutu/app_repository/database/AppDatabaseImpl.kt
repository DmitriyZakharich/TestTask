package com.example.testtasktutu.app_repository.database

import com.example.testtasktutu.app_repository.database.DatabaseHelper.getDao
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData

class AppDatabaseImpl : AppDatabase {

    override suspend fun loadGithubRepositoriesList(login: String): List<GithubRepoBriefInfoData> {
            return getDao().getGithubReposList(login) ?: emptyList()
    }

    override suspend fun updateData(listInsert: List<GithubRepoInfoData>) {
            listInsert[0].login?.let { getDao().delete(login = it) }
        getDao().insert(list = listInsert)
    }

    override suspend fun loadRepositoryInfo(login: String, name: String): GithubRepoInfoData {
        return getDao().getGithubRepoInfo(login = login, name = name)
    }

    override suspend fun updateData(detailsInfoData: GithubRepoInfoData) {

        if (!detailsInfoData.login.isNullOrEmpty() && !detailsInfoData.name.isNullOrEmpty()){
            val id = getDao().getGithubRepoInfo(
                login = detailsInfoData.login!!,
                name = detailsInfoData.name!!)
                .id
            detailsInfoData.id = id
            getDao().updateRepositoryInfo(detailsInfoData)
        }
    }
}