package com.example.testtasktutu.app_repository.network

import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApiGithubRepoDetailsInfo {
    @GET("repos/{username}/{repository}")
    suspend fun getRequest(
            @Path("username") username: String,
            @Path("repository") repository: String): Response<GithubRepoInfoData>
}

