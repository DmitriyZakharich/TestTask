package com.example.data.network

import com.example.data.interfaces.NetworkLoader
import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

class NetworkLoaderImpl(private val retrofitHelper: RetrofitHelper) : NetworkLoader {

    override suspend fun loadUsersList(): List<UserShortData> {
        val githubApiService = retrofitHelper.getRetrofit().create(GithubApiService::class.java)
        val response = githubApiService.getUsersList()
        return response.body() ?: emptyList()
    }

    override suspend fun loadUserDetailsData(login: String): UserDetailsData {
        val githubApiService = retrofitHelper.getRetrofit().create(GithubApiService::class.java)
        val response = githubApiService.getUserDetails(login = login)
        return response.body() ?: UserDetailsData(id = null, name = null, login = "", avatarUrl = null, createdAt = null, location = null)
    }
}