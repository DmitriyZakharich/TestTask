package com.example.testtasktutu.list_screen.data.network

import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApiRepositories {
    @GET("users/{username}/repos")
    fun getRequest(@Path("username") username: String): Call<List<RepositoryBriefInfoData>>
}