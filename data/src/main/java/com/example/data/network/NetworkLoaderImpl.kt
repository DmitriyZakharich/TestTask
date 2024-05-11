package com.example.data.network

import com.example.data.interfaces.NetworkLoader
import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData
import com.example.data.response.DetailsResponseResult
import com.example.data.response.ListResponseResult

class NetworkLoaderImpl(private val retrofitHelper: RetrofitHelper) : NetworkLoader {

    override suspend fun loadUsersList(): ListResponseResult {
        val githubApiService = retrofitHelper.getRetrofit().create(GithubApiService::class.java)
        val response = githubApiService.getUsersList()

        return if (response.isSuccessful && response.body() != null) {
            ListResponseResult.Success(response.body()!!)
        } else {
            ListResponseResult.Failure
        }
    }

    override suspend fun loadUserDetailsData(login: String): DetailsResponseResult {
        val githubApiService = retrofitHelper.getRetrofit().create(GithubApiService::class.java)
        val response = githubApiService.getUserDetails(login = login)

        return if (response.isSuccessful && response.body() != null) {
            DetailsResponseResult.Success(response.body()!!)
        } else {
            DetailsResponseResult.Failure
        }
    }
}