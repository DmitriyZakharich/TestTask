package com.example.testtasktutu.list_screen.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.domain.RepositoryInfo
import com.example.testtasktutu.list_screen.domain.interfaces.RetrofitRepositoriesLoaderInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepositoriesLoader : RetrofitRepositoriesLoaderInterface {

    private val _liveData: MutableLiveData<List<RepositoryInfo>>? = null
    override val liveData: LiveData<List<RepositoryInfo>>? = _liveData


    override fun loadData(query: String) {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryInfo>> {

            override fun onFailure(call: Call<List<RepositoryInfo>>, t: Throwable) {}

            override fun onResponse(
                call: Call<List<RepositoryInfo>>,
                response: Response<List<RepositoryInfo>>
            ) {

                if (response.isSuccessful) {
                    _liveData?.value = response.body()
                }

//                if (i )
                error("${response.code()} ${response.message()} ${response.errorBody()}")


            }
        })

    }

}