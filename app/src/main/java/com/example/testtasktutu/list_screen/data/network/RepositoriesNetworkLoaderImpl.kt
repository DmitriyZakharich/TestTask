package com.example.testtasktutu.list_screen.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NetworkExceptions
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesNetworkLoaderImpl : RepositoriesNetworkLoader {

    private val _liveData: MutableLiveData<List<RepositoryInfoDomain>> = MutableLiveData()
    override val liveData: LiveData<List<RepositoryInfoDomain>> = _liveData

    override fun loadData(query: String) {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryInfoDomain>> {

            override fun onFailure(call: Call<List<RepositoryInfoDomain>>, t: Throwable) {}

            override fun onResponse(call: Call<List<RepositoryInfoDomain>>,
                    response: Response<List<RepositoryInfoDomain>>) {
                if (response.isSuccessful) {
                    _liveData.value = response.body()
                } else {
                    throw NetworkExceptions(response.code(), response.message())
//                    error("${response.code()} ${response.message()} ${response.errorBody()}")
                }
            }
        })
    }
}