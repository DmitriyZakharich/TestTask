package com.example.testtasktutu.list_screen.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NetworkExceptions
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesNetworkLoaderImpl : RepositoriesNetworkLoader {

//    private val _liveData: MutableLiveData<List<RepositoryInfoData>> = MutableLiveData()
//    override val liveData: LiveData<List<RepositoryInfoData>> = _liveData

    override fun loadData(query: String, callbackList: (list: List<RepositoryInfoData>) -> Unit) {
        Log.d("TAG123321", "RepositoriesNetworkLoaderImpl = loadData")

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryInfoData>> {

            override fun onFailure(call: Call<List<RepositoryInfoData>>, t: Throwable) {}

            override fun onResponse(call: Call<List<RepositoryInfoData>>,
                    response: Response<List<RepositoryInfoData>>) {
                if (response.isSuccessful) {
                    Log.d("TAG123321", "RepositoriesNetworkLoaderImpl response.body() = ${response.body()}")

                    callbackList(response.body()!!)
                } else {
                    throw NetworkExceptions(response.code(), response.message())
//                    error("${response.code()} ${response.message()} ${response.errorBody()}")
                }
            }
        })
    }
}