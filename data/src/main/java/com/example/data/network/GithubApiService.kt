package com.example.data.network

import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("users")
    suspend fun getUsersList(): Response<List<UserShortData>>

    @GET("users/{login}")
    suspend fun getUserDetails(
        @Path("login") login: String
    ): Response<UserDetailsData>
}