package com.example.testtasktutu.list_screen.data.network

import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesNetworkLoaderImpl : RepositoriesNetworkLoader {

    override fun loadData(query: String,
            callbackList: (isSuccess: Boolean, login: String, list: List<RepositoryInfoData>?) -> Unit) {

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryInfoData>> {

            override fun onFailure(call: Call<List<RepositoryInfoData>>, t: Throwable) {}

            override fun onResponse(call: Call<List<RepositoryInfoData>>,
                    response: Response<List<RepositoryInfoData>>) {
                callbackList(response.isSuccessful, query, response.body())
            }
        })
    }
}

