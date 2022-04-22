package com.example.testtasktutu.app_data.network

import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApiRepositoryDetailsInfo {
    @GET("repos/{username}/{repository}")
    fun getRequest(@Path("username") username: String,
            @Path("repository") repository: String): Call<RepositoriesInfoData>
}

