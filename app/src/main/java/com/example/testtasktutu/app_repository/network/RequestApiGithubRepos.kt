package com.example.testtasktutu.app_repository.network

import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApiGithubRepos {
    @GET("users/{username}/repos")
    suspend fun getRequest(@Path("username") username: String): Response<List<GithubRepoBriefInfoData>>
}