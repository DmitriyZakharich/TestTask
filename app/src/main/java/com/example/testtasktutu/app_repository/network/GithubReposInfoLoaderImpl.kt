package com.example.testtasktutu.app_repository.network

import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.network.RetrofitHelper.getRetrofit

/**
 * Network loader
 * */
class GithubReposInfoLoaderImpl : GithubReposInfoLoader {

    override suspend fun loadGithubReposList(query: String): List<GithubRepoBriefInfoData> {
        val requestApiGithubRepos = getRetrofit().create(RequestApiGithubRepos::class.java)
        val response = requestApiGithubRepos.getRequest(query)
        response.body()?.map { it.login = query }
        return response.body() ?: emptyList()
    }

    override suspend fun loadGithubRepoInfo(login: String, name: String): GithubRepoInfoData {
        val requestApiRepositories = getRetrofit().create(RequestApiGithubRepoDetailsInfo::class.java)
        val response = requestApiRepositories.getRequest(login, name)
        response.body()?.login = login
        return response.body() ?: GithubRepoInfoData()
    }
}