package com.example.testtasktutu.list_screen.data.network

import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesNetworkLoaderImpl : RepositoriesNetworkLoader {

    override fun loadData(query: String,
            callbackList: (isSuccess: Boolean, login: String, list: List<RepositoryBriefInfoData>?) -> Unit) {

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryBriefInfoData>> {

            override fun onFailure(call: Call<List<RepositoryBriefInfoData>>, t: Throwable) {}

            override fun onResponse(call: Call<List<RepositoryBriefInfoData>>,
                    response: Response<List<RepositoryBriefInfoData>>) {
                callbackList(response.isSuccessful, query, response.body())
            }
        })
    }
}