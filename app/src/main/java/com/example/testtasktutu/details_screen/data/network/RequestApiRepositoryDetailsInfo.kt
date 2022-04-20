package com.example.testtasktutu.details_screen.data.network

import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApiRepositoryDetailsInfo {
    @GET("repos/{username}/{repository}")
    fun getRequest(@Path("username") username: String,
            @Path("repository") repository: String): Call<DetailsInfoData>
}

